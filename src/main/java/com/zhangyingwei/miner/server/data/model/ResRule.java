package com.zhangyingwei.miner.server.data.model;

import com.zhangyingwei.miner.server.utils.MinerEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: zhangyw
 * @date: 2018/4/4
 * @time: 下午3:17
 * @desc:
 */
@Getter
@Setter
@ToString
public class ResRule {
    private Integer id;
    private String url;
    private String rule;
    private String prefix;
    private String suffix;
    private String attr;
    private String rtype;

    public List<String> bulidRules(){
        return Arrays.stream(rule.split(MinerEnum.RULE_SPLITER.getValue())).collect(Collectors.toList());
    }
    public Boolean isEmpty() {
        return StringUtils.isBlank(rule);
    }
}
