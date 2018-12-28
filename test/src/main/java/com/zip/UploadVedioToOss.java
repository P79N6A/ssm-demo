package com.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Base64;
import java.util.stream.Stream;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.vod.upload.impl.UploadImageImpl;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadFileStreamRequest;
import com.aliyun.vod.upload.req.UploadImageRequest;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.req.UploadURLStreamRequest;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadFileStreamResponse;
import com.aliyun.vod.upload.resp.UploadImageResponse;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyun.vod.upload.resp.UploadURLStreamResponse;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.fc.bean.VideoInfoDO;
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

    /**
     * 上传图片到mmsite
     *
     * @param key
     * @param filePath
     */
    public static void uploadImg(String key, String filePath) {
        OSSClientBuilder ossClientBuilder = new OSSClientBuilder();
        OSS client = ossClientBuilder.build(END_POINT, ACCESS_KEY_ID, ACCESS_SECRET);
        client.putObject(BUCKET_NAME, key, new File(filePath));
        System.out.println(key);
        client.shutdown();
    }

    public static void picTrack() throws Exception {
        String[] pics = {
                "安.png",
                "摄影一族.png",
                "过早.png",
                "绘画家.png"
        };
        int i = 0;
        StringBuilder url = new StringBuilder();
        url.append("https://mmsite.alicdn.com/hubei.jpg?x-oss-process=image/watermark,");
        for (String pic : pics) {
            String base64Thumb = Base64.getEncoder().encodeToString(pic.getBytes("UTF-8"));
            base64Thumb = base64Thumb.replaceAll("\\+", "-").replaceAll("/", "_");
            url.append("image_").append(base64Thumb);
            if (i < pics.length - 1) {
                url.append(",t_100/watermark,");
            }
            i++;
        }
        System.out.println(url.toString());
    }

    public static void main(String[] args) throws Exception {
        /*String key = "绘画家.png";
        uploadImg(key, "C:/Users/fangcong/Desktop/Icon元素/Icon/兴趣/" + key);
        picTrack();*/
        String referer = "http://my.daily.taobao.net/apple";
        URL referUrl = new URL(referer);
        String commonReferCheckServerName = referUrl.getHost();
        System.out.println(commonReferCheckServerName);

        System.out.println(referer.indexOf("simba.taobao.com"));

        //一、视频文件上传
        //batchUpload("E:/BaiduNetdiskDownload/pepsi all 1130/BGM");
        //本地文件上传
        //File file = new File("E:/BaiduNetdiskDownload/pepsi all 1130/Video 1/SFX_1_AH.mp4");
        //String title = file.getName().substring(0, file.getName().lastIndexOf("."));
        //testUploadVideo(VodUtils.YK_ACCESS_KEY, VodUtils.YK_ACCESS_SECRET, title, file.getPath(), VodUtils.YK_TEMPALTE_ID);
        //待上传视频的网络流地址
        //String url = "http://video.sample.com/sample.mp4";
        //2.网络流上传
        //testUploadURLStream("LTAIeKQLOR3DmfNG","I4OcW1KPyWxQjsRCyefc40lbwQuYie", "audio_15", "10794.mp3", "http://sxyd.sc.chinaz.com/Files/DownLoad/sound1/201811/10794.mp3");
    }

    public static void batchUpload(String basePath) {
        File fileDirectory = new File(basePath);
        if (fileDirectory.exists() && fileDirectory.isDirectory()) {
            File[] files = fileDirectory.listFiles();
            if (null != files) {
                Stream.of(files).forEach(file -> {
                    String title = file.getName().substring(0, file.getName().lastIndexOf("."));
                    testUploadVideo(VodUtils.YK_ACCESS_KEY, VodUtils.YK_ACCESS_SECRET,
                            title, file.getPath(), VodUtils.YK_TEMPALTE_ID);
                });
            }
        } else {
            System.out.println("请输入正确的目录");
        }
    }

    /**
     * 本地文件上传接口
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @param title
     * @param fileName
     */
    private static void testUploadVideo(String accessKeyId, String accessKeySecret, String title, String fileName
            , String templateId) {
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 模板组ID(可选) */
        request.setTemplateGroupId(templateId);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);

        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            System.err.println(fileName + " upload fail!");
        }
    }

    /**
     * 网络流上传接口
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @param title
     * @param fileName
     * @param url
     */
    private static void testUploadURLStream(String accessKeyId, String accessKeySecret, String title, String fileName,
                                            String url) {
        UploadURLStreamRequest request = new UploadURLStreamRequest(accessKeyId, accessKeySecret, title, fileName, url);
        /* 是否使用默认水印(可选)，指定模板组ID时，根据模板组配置确定是否使用默认水印*/
        //request.setIsShowWaterMark(true);
        /* 设置上传完成后的回调URL(可选)，建议通过点播控制台配置消息监听事件，参见文档 https://help.aliyun.com/document_detail/57029.html */
        //request.setCallback("http://callback.sample.com");
        /* 视频分类ID(可选) */
        //request.setCateId(0);
        /* 视频标签,多个用逗号分隔(可选) */
        //request.setTags("标签1,标签2");
        /* 视频描述(可选) */
        //request.setDescription("视频描述");
        /* 封面图片(可选) */
        //request.setCoverURL("http://cover.sample.com/sample.jpg");
        /* 模板组ID(可选) */
        request.setTemplateGroupId("4b8dbedab41487d129cb14334ca39085");
        /* 存储区域(可选) */
        //request.setStorageLocation("in-201703232118266-5sejdln9o.oss-cn-shanghai.aliyuncs.com");
        /* 开启默认上传进度回调 */
        // request.setPrintProgress(true);
        /* 设置自定义上传进度回调 (必须继承 ProgressListener) */
        // request.setProgressListener(new PutObjectProgressListener());
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadURLStreamResponse response = uploader.uploadURLStream(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n"); //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }

    /**
     * 文件流上传接口
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @param title
     * @param fileName
     */
    private static void testUploadFileStream(String accessKeyId, String accessKeySecret, String title,
                                             String fileName) {
        UploadFileStreamRequest request = new UploadFileStreamRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 是否使用默认水印(可选)，指定模板组ID时，根据模板组配置确定是否使用默认水印*/
        //request.setShowWaterMark(true);
        /* 设置上传完成后的回调URL(可选)，建议通过点播控制台配置消息监听事件，参见文档 https://help.aliyun.com/document_detail/57029.html */
        //request.setCallback("http://callback.sample.com");
        /* 视频分类ID(可选) */
        //request.setCateId(0);
        /* 视频标签,多个用逗号分隔(可选) */
        //request.setTags("标签1,标签2");
        /* 视频描述(可选) */
        //request.setDescription("视频描述");
        /* 封面图片(可选) */
        //request.setCoverURL("http://cover.sample.com/sample.jpg");
        /* 模板组ID(可选) */
        //request.setTemplateGroupId("8c4792cbc8694e7084fd5330e56a33d");
        /* 存储区域(可选) */
        //request.setStorageLocation("in-201703232118266-5sejdln9o.oss-cn-shanghai.aliyuncs.com");
        /* 开启默认上传进度回调 */
        // request.setPrintProgress(true);
        /* 设置自定义上传进度回调 (必须继承 ProgressListener) */
        // request.setProgressListener(new PutObjectProgressListener());
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadFileStreamResponse response = uploader.uploadFileStream(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n"); //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }

    /**
     * 流式上传接口
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @param title
     * @param fileName
     * @param inputStream
     */
    private static void testUploadStream(String accessKeyId, String accessKeySecret, String title, String fileName,
                                         InputStream inputStream) {
        UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, fileName,
                inputStream);
         /* 是否使用默认水印(可选)，指定模板组ID时，根据模板组配置确定是否使用默认水印*/
        //request.setShowWaterMark(true);
        /* 设置上传完成后的回调URL(可选)，建议通过点播控制台配置消息监听事件，参见文档 https://help.aliyun.com/document_detail/57029.html */
        //request.setCallback("http://callback.sample.com");
        /* 视频分类ID(可选) */
        //request.setCateId(0);
        /* 视频标签,多个用逗号分隔(可选) */
        //request.setTags("标签1,标签2");
        /* 视频描述(可选) */
        //request.setDescription("视频描述");
        /* 封面图片(可选) */
        //request.setCoverURL("http://cover.sample.com/sample.jpg");
        /* 模板组ID(可选) */
        //request.setTemplateGroupId("8c4792cbc8694e7084fd5330e56a33d");
        /* 存储区域(可选) */
        //request.setStorageLocation("in-201703232118266-5sejdln9o.oss-cn-shanghai.aliyuncs.com");
        /* 开启默认上传进度回调 */
        // request.setPrintProgress(true);
        /* 设置自定义上传进度回调 (必须继承 ProgressListener) */
        // request.setProgressListener(new PutObjectProgressListener());
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }

    /**
     * 图片上传接口，本地文件上传示例
     * 参数参考文档 https://help.aliyun.com/document_detail/55619.html
     */
    private static void testUploadImageLocalFile(String title, String fileName) {
        UploadImageRequest request = new UploadImageRequest(VodUtils.fc_key, VodUtils.fc_key_secret,
                "default");
        /* 图片类型（必选）取值范围：default（默认)，cover（封面），watermark（水印）*/
        // request.setImageType("cover");
        /* 图片文件扩展名（可选）取值范围：png，jpg，jpeg */
        //request.setImageExt("png");
        /* 图片标题（可选）长度不超过128个字节，UTF8编码 */
        request.setTitle(title);
        /* 图片标签（可选）单个标签不超过32字节，最多不超过16个标签，多个用逗号分隔，UTF8编码 */
        //request.setTags("标签1,标签2");
        /* 存储区域（可选）*/
        //request.setStorageLocation("out-4f3952f78c0211e8b3020013e7.oss-cn-shanghai.aliyuncs.com");
        /* 流式上传时，InputStream为必选，fileName为源文件名称，如:文件名称.png(可选)*/
        request.setFileName(fileName);
        /* 开启默认上传进度回调 */
        // request.setPrintProgress(true);
        /* 设置自定义上传进度回调 (必须继承 ProgressListener) */
        // request.setProgressListener(new PutObjectProgressListener());
        UploadImageImpl uploadImage = new UploadImageImpl();
        UploadImageResponse response = uploadImage.upload(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");
        System.out.print("ErrorCode=" + response.getCode() + "\n");
        System.out.print("ErrorMessage" + response.getMessage() + "\n");
        System.out.print("ImageId=" + response.getImageId() + "\n");
        System.out.print("ImageURL=" + response.getImageURL() + "\n");
    }

    /**
     * 图片上传接口，流式上传示例（支持文件流和网络流）
     * 参数参考文档 https://help.aliyun.com/document_detail/55619.html
     *
     * @param accessKeyId
     * @param accessKeySecret
     */
    private static void testUploadImageStream(String accessKeyId, String accessKeySecret, String imageType) {
        UploadImageRequest request = new UploadImageRequest(accessKeyId, accessKeySecret, imageType);
        /* 图片类型（必选）取值范围：default（默认)，cover（封面），watermark（水印）*/
        // request.setImageType("cover");
        /* 图片文件扩展名（可选）取值范围：png，jpg，jpeg */
        //request.setImageExt("png");
        /* 图片标题（可选）长度不超过128个字节，UTF8编码 */
        //request.setTitle("图片标题");
        /* 图片标签（可选）单个标签不超过32字节，最多不超过16个标签，多个用逗号分隔，UTF8编码 */
        //request.setTags("标签1,标签2");
        /* 存储区域（可选）*/
        //request.setStorageLocation("out-4f3952f78c0211e8b3020013e7.oss-cn-shanghai.aliyuncs.com");
        /* 流式上传时，InputStream为必选，fileName为源文件名称，如:文件名称.png(可选)*/
        //request.setFileName("测试文件名称.png");
        /* 开启默认上传进度回调 */
        // request.setPrintProgress(true);
        /* 设置自定义上传进度回调 (必须继承 ProgressListener) */
        // request.setProgressListener(new PutObjectProgressListener());
        // 1.文件流上传
        // InputStream fileStream = getFileStream(request.getFileName());
        // if (fileStream != null) {
        //     request.setInputStream(fileStream);
        // }
        // 2.网络流上传
        // String url = "http://image.sample.com/sample.png";
        // InputStream urlStream = getUrlStream(url);
        // if (urlStream != null) {
        //     request.setInputStream(urlStream);
        // }
        // 开始上传图片
        UploadImageImpl uploadImage = new UploadImageImpl();
        UploadImageResponse response = uploadImage.upload(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");
        System.out.print("ErrorCode=" + response.getCode() + "\n");
        System.out.print("ErrorMessage" + response.getMessage() + "\n");
        System.out.print("ImageId=" + response.getImageId() + "\n");
        System.out.print("ImageURL=" + response.getImageURL() + "\n");
    }

    private static InputStream getFileStream(String fileName) {
        try {
            return new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static InputStream getUrlStream(String url) {
        try {
            return new URL(url).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
