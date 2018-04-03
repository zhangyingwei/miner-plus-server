package com.zhangyingwei.miner.server.data.service;


import com.zhangyingwei.miner.server.data.mapper.ResourcesMapper;
import com.zhangyingwei.miner.server.data.model.Resources;
import com.zhangyingwei.miner.server.exception.MinerServerException;
import com.zhangyingwei.miner.server.utils.MybatisUtils;

import java.util.List;

/**
 * Created by zhangyw on 2017/8/15.
 */
public class ResourcesService implements IResourcesService {

    private ResourcesMapper mapper = MybatisUtils.getMapper(ResourcesMapper.class);

    @Override
    public List<Resources> listNolamResources() throws MinerServerException {
        try {
            return mapper.listResourcesNomal();
        } catch (Exception e) {
            throw new MinerServerException(e.getLocalizedMessage());
        }
    }

    @Override
    public void markResourcesAsUnValid(String resources) throws MinerServerException {
        try {
            this.mapper.UpdateFlagByResources(resources,Resources.FLAG_UNVALID);
        } catch (Exception e) {
            throw new MinerServerException(e.getLocalizedMessage());
        }
    }
}
