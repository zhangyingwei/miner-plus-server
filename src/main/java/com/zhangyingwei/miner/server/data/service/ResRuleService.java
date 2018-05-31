package com.zhangyingwei.miner.server.data.service;

import com.zhangyingwei.miner.server.data.mapper.RuleMapper;
import com.zhangyingwei.miner.server.data.model.ResRule;
import com.zhangyingwei.miner.server.exception.MinerServerException;
import com.zhangyingwei.miner.server.utils.MybatisUtils;

import java.util.List;

/**
 * @author: zhangyw
 * @date: 2018/4/4
 * @time: 下午3:20
 * @desc:
 */
public class ResRuleService implements IResRuleService {
    private RuleMapper ruleMapper;

    public ResRuleService() {
        this.ruleMapper = MybatisUtils.getMapper(RuleMapper.class);
    }

    @Override
    public List<ResRule> listRulesByUrl(String url) throws MinerServerException {
        try {
            return this.ruleMapper.listRulesByUrl(url);
        } catch (Exception e) {
            throw new MinerServerException(e.getLocalizedMessage());
        }
    }

    public List<String> listUrls() throws MinerServerException{
        try {
            return this.ruleMapper.listAllUrls();
        } catch (Exception e) {
            throw new MinerServerException(e.getLocalizedMessage());
        }
    }
}
