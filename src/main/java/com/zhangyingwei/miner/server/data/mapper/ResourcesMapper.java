package com.zhangyingwei.miner.server.data.mapper;

import com.zhangyingwei.miner.server.data.model.Resources;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author: zhangyw
 * @date: 2018/4/3
 * @time: 下午8:22
 * @desc:
 */
public interface ResourcesMapper {

    @Select("select * from mp_resources where flag=1")
    List<Resources> listResourcesNomal() throws Exception;

    @Update("update mp_resources set flag=#{flag} where resources=#{resources}")
    void UpdateFlagByResources(@Param("resources") String resources, @Param("flag") Integer flag) throws Exception;
}
