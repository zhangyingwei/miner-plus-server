package com.zhangyingwei.miner.server.utils;

import lombok.Getter;

/**
 * Created by zhangyw on 2018/4/2.
 */
public enum MinerEnum {
    RULE_SPLITER("@"),
    ATTR_TEXT("text"),;

    @Getter
    private String value;
    MinerEnum(String value) {
        this.value = value;
    }
}
