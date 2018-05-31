package com.zhangyingwei.miner.server.data.service;

import com.zhangyingwei.miner.server.data.mapper.ContentMapper;
import com.zhangyingwei.miner.server.data.model.Content;
import com.zhangyingwei.miner.server.exception.MinerServerException;
import com.zhangyingwei.miner.server.utils.MybatisUtils;

import java.util.List;

/**
 * @author: zhangyw
 * @date: 2018/4/4
 * @time: 下午4:56
 * @desc:
 */
public class ContentService implements IContentService {

    private ContentMapper contentMapper;

    public ContentService() {
        this.contentMapper = MybatisUtils.getMapper(ContentMapper.class);
    }

    @Override
    public List<String> listAllUrls() throws MinerServerException {
        try {
            return this.contentMapper.listAllUrls();
        } catch (Exception e) {
            throw new MinerServerException(e.getLocalizedMessage());
        }
    }

    @Override
    public void saveOne(Content content) throws MinerServerException {
        try {
            this.contentMapper.saveOne(content);
        } catch (Exception e) {
            throw new MinerServerException(e.getLocalizedMessage());
        }
    }
}
