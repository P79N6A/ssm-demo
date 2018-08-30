package com.exams;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * @author fangcong on 2018/8/23.
 */
@TargetUrl("https://image.baidu.com/search/index?ct=201326592&z=&tn=baiduimage&ipn=r&word=壁纸 不同风格 美女&pn=0.*")
public class WeiBoRobot {

    public static void main(String[] args) {
        OOSpider.create(Site.me().setRetryTimes(3).setSleepTime(1000),
            new DefPageModelPipeline(), WeiBoRepo.class)
            .addUrl(
                "https://image.baidu.com/search/index?ct=201326592&z=&tn=baiduimage&ipn=r&word=壁纸 不同风格 "
                    + "美女&pn=0&istype=2&ie=utf-8&oe=utf-8&cl=2"
                    + "&lm=-1&st=-1&fr=&fmq=1526269427171_R&ic=0&se=&sme=&width=&height=&face=0")
            .thread(4).run();
    }
}
