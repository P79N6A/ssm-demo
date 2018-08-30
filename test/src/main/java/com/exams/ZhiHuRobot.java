package com.exams;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.pipeline.JsonFilePageModelPipeline;

/**
 * 注解方式实现和<class>ZhihuPageProcessor</class>相同的功能
 *
 * @author fangcong on 2018/8/23.
 */
@TargetUrl("https://www\\.zhihu\\.com/question/\\d+/answer/\\d+.*")
public class ZhiHuRobot {

    public static void main(String[] args) {
        OOSpider.create(Site.me().setRetryTimes(3).setSleepTime(1000),
            new JsonFilePageModelPipeline("F:\\test\\webmagic\\"), ZhiHuRepo.class)
            .addUrl("https://www.zhihu.com/explore").thread(5).run();
    }
}
