package com.zip;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadURLStreamRequest;
import com.aliyun.vod.upload.resp.UploadURLStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.ProduceEditingProjectVideoRequest;
import com.aliyuncs.vod.model.v20170321.ProduceEditingProjectVideoResponse;

/**
 * @author fangcong on 2018/11/20.
 */
public class UploadAudio {

    /**
     * 初始化self客户端
     *
     * @param args
     * @throws Exception
     */
    private static final DefaultAcsClient FC_CLIENT = new DefaultAcsClient(
        DefaultProfile.getProfile("cn-shanghai", VodUtils.fc_key, VodUtils.fc_key_secret)
    );

    /**
     * 1、上传视频和音频，可以指定模板
     * 2、单独合成视频或音频，不需要指定ProduceConfig
     * 3、视频与音频合成时，指定ProduceConfig为M3U8
     * 4、合成视频直接拼接时，指定ProduceConfig为M3U8,ProcessMode为Joint
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // 3、视频拼接
        //PalyVideo.heChengVideos(2, FC_CLIENT, "07b655ffc13743e6b9b9add529e834b3", "eb59c99f547346349900de09e38dee9b");

        // 2、视频+音频
        //PalyVideo.videoAudioTracks("7c9c21445e8947419c822a60b6ab9380", "fdafec2e2ece4510b96cf74b2bb7dbba", FC_CLIENT);
        //uploadUrlStream(VodUtils.fc_key, VodUtils.fc_key_secret, "5442", "5442.mp3", "http://jsdx.sc.chinaz
        // .com/Files/DownLoad/sound1/201501/5442.mp3");
        // audioTrack("58534b24629540bbbb4ae349becd3314","0bb3de9e306c4a48bd9a3b1ac801e034");

        // 4、一段视频+两段音频
        videoTrackAudio("670eebf913e94beb82a9fc564584a5bb", "58534b24629540bbbb4ae349becd3314", "0bb3de9e306c4a48bd9a3b1ac801e034");
    }

    /**
     * 1、音频合成拼接
     *
     * @param audio1
     * @param audio2
     * @throws Exception
     */
    public static void audioTrack(String audio1, String audio2) throws Exception {
        ProduceEditingProjectVideoRequest request = new ProduceEditingProjectVideoRequest();
        // Timeline
        StringBuilder sb = new StringBuilder();

        sb.append("{\"AudioTracks\":[{\"AudioTrackClips\":[");
        sb.append("{\"MediaId\":\"").append(audio1).append("\", \"In\":0, \"Out\":5}");
        sb.append(",{\"MediaId\":\"").append(audio2).append("\", \"In\":0, \"Out\":10}");
        sb.append("]}]}");
        String timeLine = sb.toString();
        System.out.println("timeLine:" + timeLine);
        request.setTimeline(timeLine);

        ProduceEditingProjectVideoResponse response = FC_CLIENT.getAcsResponse(request);
        System.out.println(response.getMediaId());
    }

    /**
     * 视频上加上音频
     *
     * @param videoId 视频id
     * @param audio1  第一段音频
     * @param audio2  第二段音频
     */
    public static void videoTrackAudio(String videoId, String audio1, String audio2) throws Exception {
        StringBuilder sb = new StringBuilder();
        // 要拼接的视频
        sb.append("{\"VideoTracks\":[{\"VideoTrackClips\": [");
        sb.append("{\"MediaId\":\"").append(videoId).append("\",\"Effects\":[{\"Type\":\"Volume\",\"Gain\":\"0\"}]");
        sb.append("}]}]");
        // 加上两段音频
        sb.append(",\"AudioTracks\":[{\"AudioTrackClips\":[")
            .append("{\"MediaId\":\"").append(audio1).append("\", \"TimelineIn\":0")
            .append(",\"Effects\":[{\"Type\":\"Volume\",\"Gain\":\"1\"}]}")
            .append("]}")
            .append(",{\"AudioTrackClips\":[")
            .append("{\"MediaId\":\"").append(audio2).append("\", \"TimelineIn\":5")
            .append(",\"Effects\":[{\"Type\":\"Volume\",\"Gain\":\"1\"}]}")
            .append("]}]}");
        String track = sb.toString();
        System.out.println(track);
        ProduceEditingProjectVideoRequest request = new ProduceEditingProjectVideoRequest();
        // 核心参数设置
        request.setTimeline(track);
        // 设置produceConfig
        request.setProduceConfig("{\"ContainerFormat\":\"M3U8\"}");
        //request.setProduceConfig("{\"ContainerFormat\":\"M3U8\",\"ProcessMode\":\"Joint\"}");
        ProduceEditingProjectVideoResponse response = FC_CLIENT.getAcsResponse(request);
        System.out.println(response.getMediaId());
    }

    /**
     * 网络URL流上传
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @param title
     * @param fileName
     * @param url
     */
    public static void uploadUrlStream(String accessKeyId, String accessKeySecret, String title, String fileName,
                                       String url) {
        UploadURLStreamRequest request = new UploadURLStreamRequest(accessKeyId, accessKeySecret, title, fileName, url);
        // 模板组ID(可选)
        request.setTemplateGroupId("6719e14a2b5e72f7c99d042dac1c49b8");
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadURLStreamResponse response = uploader.uploadURLStream(request);
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            System.out.println(response.getMessage());
        }
    }
}
