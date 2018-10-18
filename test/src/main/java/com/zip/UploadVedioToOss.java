package com.zip;

import java.io.File;

import com.aliyun.oss.OSSClient;
import org.springframework.util.DigestUtils;

/**
 * @author fangcong on 2018/10/18.
 */
public class UploadVedioToOss {

    private static final String END_POINT = "cn-hangzhou.oss-pub.aliyun-inc.com";

    private static final String ACCESS_KEY_ID = "5QXCa91zAm1uNdWP";

    private static final String ACCESS_SECRET = "rdQydZYIp6OGZ1GxUt0XR11KrAv0RE";

    private static final String BUCKET_NAME = "ss-site-simba";

    private static final String URL_PREFIX = "mmsite.alicdn.com/";


    public static void main(String[] args) {
        String file = "F:/test/vedio/对象OSS存储简介.mp4";
        String suffix = ".mp4";
        String fileName = file.substring(file.lastIndexOf("/") + 1, file.length() - 4);
        System.out.println("fileName : " + fileName);
        String key = DigestUtils.md5DigestAsHex(fileName.getBytes());

        OSSClient ossClient = new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_SECRET);

        // 要key后面加上后缀
        ossClient.putObject(BUCKET_NAME, key + suffix, new File(file));
        System.out.println("url : " + URL_PREFIX + key + suffix);
    }
}
