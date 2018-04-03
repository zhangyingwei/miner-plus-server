package com.zhangyingwei.miner.server.data.service;


import com.zhangyingwei.miner.server.data.model.Resources;
import com.zhangyingwei.miner.server.exception.MinerServerException;

import java.util.List;

/**
 * Created by zhangyw on 2017/8/15.
 */
public interface IResourcesService {
    List<Resources> listNolamResources() throws MinerServerException;
    void markResourcesAsUnValid(String resources) throws MinerServerException;
}
