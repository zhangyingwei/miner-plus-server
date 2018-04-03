package com.zhangyingwei.miner.server.store;

import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.store.IStore;

/**
 * @author: zhangyw
 * @date: 2018/4/3
 * @time: 下午9:20
 * @desc:
 */
public class MinerStore implements IStore {
    private RssStore rssStore;
    private SiteStore siteStore;

    public MinerStore() {
        this.rssStore = new RssStore();
        this.siteStore = new SiteStore();
    }

    @Override
    public void store(TaskResponse taskResponse) throws Exception {
        if (taskResponse.isGroup("rss")) {
            this.rssStore.store(taskResponse);
        } else if (taskResponse.isGroup("site")) {
            this.siteStore.store(taskResponse);
        }
    }
}
