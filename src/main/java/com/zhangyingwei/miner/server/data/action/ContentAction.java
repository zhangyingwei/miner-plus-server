package com.zhangyingwei.miner.server.data.action;

import com.zhangyingwei.miner.server.data.model.Content;
import com.zhangyingwei.miner.server.data.service.ContentService;
import com.zhangyingwei.miner.server.exception.MinerServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author: zhangyw
 * @date: 2018/4/4
 * @time: 下午5:02
 * @desc:
 */
public class ContentAction {

    private List<String> urls;
    private ContentService contentService;
    private Logger logger = LoggerFactory.getLogger(ContentAction.class);

    public ContentAction() {
        this.contentService = new ContentService();
        try {
            this.urls = this.contentService.listAllUrls();
        } catch (MinerServerException e) {
            e.printStackTrace();
        }
    }

    public void saveContent(Content content) throws MinerServerException {
        if (!urls.contains(content.getUrl())) {
            this.contentService.saveOne(content);
            logger.info("保存文章 {} - {} 成功", content.getSitename(), content.getTitle());
        } else {
            logger.info("{} - {}  是一篇旧文章",content.getSitename(),content.getTitle());
        }
    }
}
