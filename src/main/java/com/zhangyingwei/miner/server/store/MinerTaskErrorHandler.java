package com.zhangyingwei.miner.server.store;

import com.zhangyingwei.cockroach.executer.response.TaskErrorResponse;
import com.zhangyingwei.cockroach.http.handler.ITaskErrorHandler;
import com.zhangyingwei.miner.server.data.action.ResourcesAction;
import com.zhangyingwei.miner.server.exception.MinerServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: zhangyw
 * @date: 2018/4/3
 * @time: 下午9:26
 * @desc:
 */
public class MinerTaskErrorHandler implements ITaskErrorHandler {
    private ResourcesAction resourcesAction;
    private Logger logger = LoggerFactory.getLogger(MinerTaskErrorHandler.class);

    public MinerTaskErrorHandler() {
        this.resourcesAction = new ResourcesAction();
    }

    @Override
    public void error(TaskErrorResponse taskErrorResponse) {
        try {
            resourcesAction.unvalid(taskErrorResponse.getTask());
        } catch (MinerServerException e) {
            logger.info(e.getLocalizedMessage());
        }
    }
}
