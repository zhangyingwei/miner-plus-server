package com.zhangyingwei.miner.server.data.mapper;

import com.zhangyingwei.miner.server.data.model.Content;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: zhangyw
 * @date: 2018/4/3
 * @time: 下午8:22
 * @desc:
 */
public interface ContentMapper {

    @Select("select url from mp_content group by url")
    List<String> listAllUrls() throws Exception;

    @Insert("INSERT INTO mp_content (author, sitename, site, url, title, description, comment, pubdate, getdate, pushdate, state) VALUES (#{content.author}, #{content.sitename}, #{content.site}, #{content.url}, #{content.title}, #{content.description}, #{content.comment}, #{content.pubdate}, #{content.getdate}, #{content.pushdate}, #{content.state})")
    void saveOne(@Param("content") Content content) throws Exception;
}
