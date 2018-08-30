package com.exams;

import com.fc.file.DownImage;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

/**
 * @author fangcong on 2018/8/23.
 */
public class DefPageModelPipeline implements PageModelPipeline {

    @Override
    public void process(Object o, Task task) {
        System.out.println(ToStringBuilder.reflectionToString(o));
        if (o instanceof WeiBoRepo) {
            WeiBoRepo weiBo = (WeiBoRepo)o;
            String pictureUrl = getPictureUrl(weiBo.getPicture());
            if (null != pictureUrl) {
                String filePath = "F://test/images";
                try {
                    DownImage.download(pictureUrl, filePath + pictureUrl.substring(pictureUrl.lastIndexOf("/")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getPictureUrl(String imgTag) {
        if (StringUtils.isBlank(imgTag) || !imgTag.contains("ss1.bdstatic.com")) {
            return null;
        }
        int beginIndex = imgTag.indexOf("src");
        int endIndex = imgTag.indexOf("\"", beginIndex + 5);
        String url = imgTag.substring(beginIndex + 4, endIndex);
        if (!url.startsWith("http")) {
            url = "https:" + url;
        }
        return url;
    }
}
