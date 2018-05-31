package com.zhangyingwei.miner.server.store;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.store.IStore;
import com.zhangyingwei.miner.server.data.action.ContentAction;
import com.zhangyingwei.miner.server.data.action.ResourcesAction;
import com.zhangyingwei.miner.server.data.model.Content;
import com.zhangyingwei.miner.server.data.service.ContentService;
import com.zhangyingwei.miner.server.exception.MinerServerException;
import com.zhangyingwei.miner.server.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author: zhangyw
 * @date: 2018/4/3
 * @time: 下午9:27
 * @desc:
 */
public class RssStore implements IStore {

    private SyndFeedInput input = new SyndFeedInput();
    private ResourcesAction resourcesAction = new ResourcesAction();
    private Logger logger = LoggerFactory.getLogger(RssStore.class);
    private ContentAction contentAction = new ContentAction();

    @Override
    public void store(TaskResponse taskResponse) {
        try {
            ByteArrayInputStream inputStream=new ByteArrayInputStream(taskResponse.getContent().bytes());
            SyndFeed feed = input.build(new XmlReader(inputStream));
            List<Content> contents = new ArrayList<Content>();
            feed.getEntries().stream().forEach(en -> {
                Content content = new Content();
                content.setSite(Optional.ofNullable(feed.getUri()).orElse(feed.getLink()));
                content.setAuthor(feed.getAuthor());
                content.setSitename(feed.getTitle());
                content.setGetdate(DateUtils.getCurrentDateTime());
                content.setState(Content.STATE_INIT);
                content.setTitle(en.getTitle());
                content.setUrl(en.getUri());
                content.setDescription(en.getDescription().getValue());
                content.setPubdate(en.getPublishedDate().toString());
                contents.add(content);
                try {
                    this.contentAction.saveContent(content);
                } catch (MinerServerException e) {
                    logger.info("保存文章 {} - {} 失败 {}",content.getSitename(),content.getTitle(),e.getLocalizedMessage());
                }
            });
            logger.info("抓取RSS文章 {} 篇", contents.size());
        } catch (Exception e) {
            try {
                resourcesAction.unvalid(taskResponse.getTask());
                logger.info("{} 已经失效",taskResponse.getTask().getUrl());
            } catch (MinerServerException e1) {
                e1.printStackTrace();
            }
        }
    }

    private String formateChar(String value) {
        return value
                .replaceAll("\\xF0", "")
                .replaceAll("\\x9F", "")
                .replaceAll("\\x98", "")
                .replaceAll("\\x80", "");
    }
}
