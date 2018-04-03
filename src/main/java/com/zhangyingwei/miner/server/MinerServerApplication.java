package com.zhangyingwei.miner.server;

import com.zhangyingwei.cockroach.CockroachApplication;
import com.zhangyingwei.cockroach.annotation.*;
import com.zhangyingwei.cockroach.queue.CockroachQueue;
import com.zhangyingwei.miner.server.data.action.ResourcesAction;
import com.zhangyingwei.miner.server.exception.MinerServerException;
import com.zhangyingwei.miner.server.store.MinerStore;
import com.zhangyingwei.miner.server.store.MinerTaskErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: zhangyw
 * @date: 2018/4/3
 * @time: 下午8:49
 * @desc:
 */
@EnableAutoConfiguration
@AppName("Miner Server")
@Store(MinerStore.class)
@TaskErrorHandlerConfig(MinerTaskErrorHandler.class)
@AutoClose(true)
@ThreadConfig(num = 1,sleep = 500)
public class MinerServerApplication {
    private static Logger logger = LoggerFactory.getLogger(MinerServerApplication.class);
    public static void main(String[] args) throws Exception {
        ResourcesAction resourcesAction = new ResourcesAction();
        CockroachQueue queue = resourcesAction.bulidTaskQueue();
        CockroachApplication.run(MinerServerApplication.class, queue);
    }
}
