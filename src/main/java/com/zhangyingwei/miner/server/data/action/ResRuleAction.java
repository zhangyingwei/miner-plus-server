package com.zhangyingwei.miner.server.data.action;

import com.zhangyingwei.miner.server.data.model.ResRule;
import com.zhangyingwei.miner.server.data.service.ResRuleService;
import com.zhangyingwei.miner.server.exception.MinerServerException;

import java.util.List;

/**
 * @author: zhangyw
 * @date: 2018/4/4
 * @time: 下午3:23
 * @desc:
 */
public class ResRuleAction {
    private ResRuleService resRuleService;

    public ResRuleAction() {
        this.resRuleService = new ResRuleService();
    }
    public List<ResRule> listRulesByUrl(String url) throws MinerServerException {
        return this.resRuleService.listRulesByUrl(url);
    }
}
