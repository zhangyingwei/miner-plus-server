package com.zhangyingwei.miner.server.data.action;


import com.zhangyingwei.cockroach.queue.CockroachQueue;
import org.junit.Test;

/**
 * @author: zhangyw
 * @date: 2018/4/3
 * @time: 下午8:41
 * @desc:
 */
public class ResourcesActionTest {
    @Test
    public void bulidTaskQueue() throws Exception {
        ResourcesAction action = new ResourcesAction();
        CockroachQueue queue = action.bulidTaskQueue();
        System.out.println(queue.take());
    }
}