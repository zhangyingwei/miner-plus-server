package com.zhangyingwei.miner.server.data.service;

import com.zhangyingwei.miner.server.data.model.ResRule;
import com.zhangyingwei.miner.server.exception.MinerServerException;

import java.util.List;

/**
 * @author: zhangyw
 * @date: 2018/4/4
 * @time: 下午3:20
 * @desc:
 */
public interface IResRuleService {
    List<ResRule> listRulesByUrl(String url) throws MinerServerException;
}
