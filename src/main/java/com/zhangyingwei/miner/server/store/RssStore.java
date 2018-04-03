package com.zhangyingwei.miner.server.store;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.store.IStore;
import com.zhangyingwei.miner.server.data.action.ResourcesAction;
import com.zhangyingwei.miner.server.exception.MinerServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

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

    @Override
    public void store(TaskResponse taskResponse) {
        try {
            ByteArrayInputStream inputStream=new ByteArrayInputStream(taskResponse.getContent().bytes());
            SyndFeed feed = input.build(new XmlReader(inputStream));
            feed.getEntries().stream().forEach(en -> {
                System.out.println(en.getTitle());
            });
        } catch (Exception e) {
            try {
                resourcesAction.unvalid(taskResponse.getTask());
                logger.info("{} 已经失效",taskResponse.getTask().getUrl());
            } catch (MinerServerException e1) {
                e1.printStackTrace();
            }
        }
    }
}
