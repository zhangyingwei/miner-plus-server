package com.zhangyingwei.miner.server.data.mapper;

import com.zhangyingwei.miner.server.data.model.ResRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: zhangyw
 * @date: 2018/4/4
 * @time: 下午3:15
 * @desc:
 */
public interface RuleMapper {
    @Select("select * from mp_resources_rules where url=#{url}")
    List<ResRule> listRulesByUrl(@Param("url") String url) throws Exception;

    @Select("select url from mp_resources_rules group by url")
    List<String> listAllUrls() throws Exception;
}