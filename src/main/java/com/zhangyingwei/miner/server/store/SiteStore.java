package com.zhangyingwei.miner.server.store;

import com.zhangyingwei.cockroach.executer.response.TaskResponse;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.store.IStore;
import com.zhangyingwei.miner.server.data.action.ContentAction;
import com.zhangyingwei.miner.server.data.action.ResRuleAction;
import com.zhangyingwei.miner.server.data.model.Content;
import com.zhangyingwei.miner.server.data.model.ResRule;
import com.zhangyingwei.miner.server.data.model.Resources;
import com.zhangyingwei.miner.server.exception.MinerServerException;
import com.zhangyingwei.miner.server.utils.DateUtils;
import com.zhangyingwei.miner.server.utils.MinerEnum;
import com.zhangyingwei.miner.server.utils.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: zhangyw
 * @date: 2018/4/3
 * @time: 下午9:27
 * @desc:
 */
public class SiteStore implements IStore {

    private Logger logger = LoggerFactory.getLogger(SiteStore.class);
    private ResRuleAction resRuleAction;
    private ContentAction contentAction = new ContentAction();

    public SiteStore() {
        this.resRuleAction = new ResRuleAction();
    }

    @Override
    public void store(TaskResponse taskResponse) throws Exception {
        String url = taskResponse.getTask().getUrl();
        List<ResRule> rules = this.resRuleAction.listRulesByUrl(url);
        if (rules.size() == 0) {
            logger.info("{} 规则未配置",url);
        }
        Map<String, ResRule> ruleMap = new HashMap<String,ResRule>();
        for (ResRule rule : rules) {
            ruleMap.put(rule.getRtype(), rule);
        }

        List<String> titles = WebUtils.select(taskResponse, ruleMap.get("title"));
        List<String> urls = WebUtils.select(taskResponse, ruleMap.get("url"));
        List<String> descs = WebUtils.select(taskResponse, ruleMap.get("desc"));
        List<String> pubdates = WebUtils.select(taskResponse, ruleMap.get("pubdate"));

        Resources resources = (Resources) taskResponse.getTask().getExtr();
        List<Content> contents = new ArrayList<Content>();
        String sitename = taskResponse.select("title").text().split("-")[0].split("\\|")[0];
        if (this.validRes(resources,titles,urls,descs,pubdates)) {
            for (int i = 0; i < titles.size(); i++) {
                Content content = new Content();
                content.setSitename(sitename);
                content.setSite(resources.getResources());
                content.setGetdate(DateUtils.getCurrentDateTime());
                content.setState(Content.STATE_INIT);
                // -----------------------------------------------
                content.setTitle(titles.get(i));
                content.setUrl(urls.get(i));
                content.setDescription(this.getIfExit(descs,i));
                content.setPubdate(this.getIfExit(pubdates, i));
                contents.add(content);
                try {
                    this.contentAction.saveContent(content);
                } catch (MinerServerException e) {
                    logger.info("保存文章 {} - {} 失败 {} ",content.getSitename(),content.getTitle(),e.getLocalizedMessage());
                }
            }
        }
        logger.info("共抓取 {} 文章 {} 篇", resources.getResources(), contents.size());
    }

    private String getIfExit(List<String> descs, int index) {
        if (descs != null && descs.size() > index) {
            return descs.get(index);
        }
        return null;
    }

    private boolean validRes(Resources resources, List<String> titles, List<String> urls, List<String> descs, List<String> pubdates) {
        boolean flag = titles.size() == urls.size();
        if (!flag) {
            logger.info("{} 验证抓取结果有效性失败",resources.getResources());
        }
        return flag;
    }
}
