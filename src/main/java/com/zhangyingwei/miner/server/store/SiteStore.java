package com.zhangyingwei.miner.server.store;

import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.store.IStore;

/**
 * @author: zhangyw
 * @date: 2018/4/3
 * @time: 下午9:27
 * @desc:
 */
public class SiteStore implements IStore {
    @Override
    public void store(TaskResponse taskResponse) throws Exception {
        String title = taskResponse.select("title").text();
        System.out.println("------------"+title);
    }
}
