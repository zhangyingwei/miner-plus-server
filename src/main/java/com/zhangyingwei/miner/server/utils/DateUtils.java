package com.zhangyingwei.miner.server.utils;

import org.joda.time.DateTime;

/**
 * @author: zhangyw
 * @date: 2018/4/4
 * @time: 下午4:20
 * @desc:
 */
public class DateUtils {
    public static String getCurrentDateTime() {
        return new DateTime().toString("yyyy-MM-dd HH:mm:ss");
    }

    public static void main(String[] args) {
        System.out.println(DateUtils.getCurrentDateTime());
    }
}
