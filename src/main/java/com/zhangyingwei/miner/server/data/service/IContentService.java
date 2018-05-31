package com.zhangyingwei.miner.server.data.service;

import com.zhangyingwei.miner.server.data.model.Content;
import com.zhangyingwei.miner.server.exception.MinerServerException;

import java.util.List;

/**
 * @author: zhangyw
 * @date: 2018/4/4
 * @time: 下午4:56
 * @desc:
 */
public interface IContentService {
    List<String> listAllUrls() throws MinerServerException;

    void saveOne(Content content) throws MinerServerException;
}
