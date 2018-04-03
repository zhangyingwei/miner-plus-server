package com.zhangyingwei.miner.server.data.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by zhangyw on 2017/8/15.
 */
@Getter
@Setter
@ToString
public class Resources {
    public static final Integer FLAG_INIT = 0;
    public static final Integer FLAG_NOMAL = 1;
    public static final Integer FLAG_UNVALID = 2;
    public static final Integer FLAG_DEL = 3;
    private Integer id;
    private String resources;
    private String rgroup;
    private String rtype;
    private String createdate;
    private String updatedate;
    private Integer flag;
}
