package com.zhangyingwei.miner.server.data.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by zhangyw on 2017/8/16.
 */
@Getter
@Setter
@ToString
public class Content {
    /**
     * 初始
     */
    public static final Integer STATE_INIT = 0;
    /**
     * 正常
     */
    public static final Integer STATE_NOMAL = 1;
    /**
     * 失效
     */
    public static final Integer STATE_INVALID = 2;
    /**
     * 删除
     */
    public static final Integer STATE_DEL = 9;
    private Integer id;
    private String author;
    private String sitename;
    private String site;
    private String title;
    private String url;
    private String description;
    private String comment;
    private String pubdate;
    private String getdate;
    private String pushdate;
    private Integer state;
}
