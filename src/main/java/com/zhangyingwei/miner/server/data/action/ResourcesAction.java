package com.zhangyingwei.miner.server.data.action;

import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import com.zhangyingwei.cockroach.queue.TaskQueue;
import com.zhangyingwei.miner.server.data.model.Resources;
import com.zhangyingwei.miner.server.data.service.ResRuleService;
import com.zhangyingwei.miner.server.data.service.ResourcesService;
import com.zhangyingwei.miner.server.exception.MinerServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by zhangyw on 2017/8/16.
 */
public class ResourcesAction {
    private Logger logger = LoggerFactory.getLogger(ResourcesAction.class);
    private ResourcesService resourcesService;
    private ResRuleService resRuleService;

    public ResourcesAction() {
        this.resourcesService = new ResourcesService();
        this.resRuleService = new ResRuleService();
    }

    public CockroachQueue bulidTaskQueue() throws Exception {
        List<Resources> res = this.resourcesService.listNolamResources();
        List<String> urls = this.resRuleService.listUrls();
        logger.info("准备入队... size is {}",res.size());
        final CockroachQueue queue = TaskQueue.of(1024).filter(task -> {
            boolean flag = "rss".equals(task.getGroup()) || urls.contains(task.getUrl());
            if (!flag) {
                logger.info("{} 未配置规则",task.getUrl());
            }
            return flag;
        });
        res.stream().map(item -> {
            Task task = new Task(item.getResources(),item.getRgroup());
            task.setExtr(item);
            return task;
        }).forEach(item -> {
            try {
                queue.push(item,true);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        });
        logger.info("入队完毕");
        return queue;
    }

    public void unvalid(Task task) throws MinerServerException {
        this.resourcesService.markResourcesAsUnValid(task.getUrl());
    }


}
