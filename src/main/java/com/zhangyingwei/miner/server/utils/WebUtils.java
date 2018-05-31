package com.zhangyingwei.miner.server.utils;

import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.miner.server.data.model.ResRule;
import com.zhangyingwei.miner.server.exception.MinerServerException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: zhangyw
 * @date: 2018/4/1
 * @time: 下午8:56
 * @desc:
 */
public class WebUtils {
    private static Logger logger = LoggerFactory.getLogger(WebUtils.class);
    private static OkHttpClient client;

    static {
        client = new OkHttpClient().newBuilder().build();
    }
    public static List<String> select(TaskResponse taskResponse, ResRule rule) throws MinerServerException {
        try {
            Elements res = taskResponse.select("*");
            List<String> rules = rule.bulidRules();
            for (String r : rules) {
                logger.info(r);
                res = res.select(r);
            }
            List<String> resRules = null;
            if (MinerEnum.ATTR_TEXT.getValue().equals(rule.getAttr()) || StringUtils.isBlank(rule.getAttr())) {
                resRules = res.stream().map(element -> element.text()).collect(Collectors.toList());
            }else{
                resRules = res.stream().map(element -> element.attr(rule.getAttr())).collect(Collectors.toList());
            }
            return resRules.stream().map(text -> {
                if (StringUtils.isNotEmpty(rule.getPrefix())) {
                    text = rule.getPrefix() + text;
                }
                if (StringUtils.isNotEmpty(rule.getSuffix())) {
                    text = text + rule.getSuffix();
                }
                return text;
            }).filter(StringUtils::isNotBlank).collect(Collectors.toList());
        } catch (Exception e) {
            throw new MinerServerException(e.getLocalizedMessage());
        }
    }
}
