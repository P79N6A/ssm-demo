package com.zip;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse.PlayInfo;
import com.aliyuncs.vod.model.v20170321.ProduceEditingProjectVideoRequest;
import com.aliyuncs.vod.model.v20170321.ProduceEditingProjectVideoResponse;
import com.fc.bean.VideoInfoDO;
import com.fc.common.JdbcUtils;

/**
 * 根据videoId获取播放地址
 *
 * @author fangcong on 2018/11/15.
 */
public class PalyVideo {

    /**
     * 旅游MV3-15秒视频
     */
    private static final String VEDIO_1_15 = "c334155793234d548e1f1e06e6798942";

    /**
     * 旅游MV2-15秒视频
     */
    private static final String VEDIO_2_15 = "196210b8f5824d5b8290da5cf86fd616";

    /**
     * 合成后的30秒视频
     */
    private static final String VEDIO_3_30 = "4e08e6a5fb794b228af45f2b2215eb99";

    public static void main(String[] args) throws Exception {
        // 1.上传音频和视频
        //testUploadVideo("audio_10", "F:/test/vedio/audio_10.mp4");
        // 2.合成视频或者合成音频
        //heChengVideos(1, VodUtils.FC_CLIENT, "65b5303cef644d38a95c27e618046397", "04ee35dc0dfd41c3be4ccba1ba95f1e4");
        //heChengVideos(2, VodUtils.FC_CLIENT, "815878ac3a8241bb9969260d6c22d065", "a80c651d1ec04ed6b100961aee3bf90e");
        // 3.音视频混合合成
        //videoAudioTracks("7c9c21445e8947419c822a60b6ab9380", "58534b24629540bbbb4ae349becd3314", "0bb3de9e306c4a48bd9a3b1ac801e034", VodUtils.FC_CLIENT);
        // 4.视频上加上合成图片
        // picTrack(IMG_01, IMG_02, IMG_03);
        getPlayInfo("b32d96d0fab040e59b639b714b9229b7", "m3u8", VodUtils.FC_CLIENT);
    }

    /**
     * 本地文件上传接口
     *
     * @param title    默认可以取文件名:file.getName()
     * @param fileName 文件path:file.getPath()
     */
    private static void testUploadVideo(String title, String fileName) {
        UploadVideoRequest request = new UploadVideoRequest(VodUtils.fc_key, VodUtils.fc_key_secret, title,
            fileName);
        request.setEnableCheckpoint(false);
        // request.setTemplateGroupId(VodUtils.fc_template_id);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            VideoInfoDO videoInfoDO = new VideoInfoDO();
            videoInfoDO.setMediaId(response.getVideoId());
            videoInfoDO.setTitle(title);
            insert(videoInfoDO);
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }

    /**
     * 根据mediaId获取播放地址
     *
     * @throws Exception
     */
    public static void getPlayInfo(String mediaId, String format, DefaultAcsClient client) throws Exception {
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId(mediaId);
        request.setFormats(format);
        GetPlayInfoResponse response = client.getAcsResponse(request);

        List<PlayInfo> list = response.getPlayInfoList();
        // 播放地址
        list.stream().forEach(playInfo -> {
            System.out.println(playInfo.getPlayURL());
        });
        // base信息
        System.out.println(response.getVideoBase().getCoverURL());
        System.out.println(response.getVideoBase().getTitle());
    }

    /**
     * 音视频合成，音频合成也可以用VideoTracks，后台会进行转码，建议音视频区分
     *
     * @param type     合成类型，合成一段时只设置M3U8如视频+视频，视频+音频
     * @param client
     * @param videoIds
     * @throws ClientException
     */
    public static void heChengVideos(int type, DefaultAcsClient client, String... videoIds) throws Exception {
        ProduceEditingProjectVideoRequest request = new ProduceEditingProjectVideoRequest();
        // Timeline
        StringBuilder sb = new StringBuilder();
        if (type == 0) {
            sb.append("{\"AudioTracks\":[{\"AudioTrackClips\":[");
        } else {
            sb.append("{\"VideoTracks\":[{\"VideoTrackClips\":[");
        }

        for (int i = 0; i < videoIds.length; i++) {
            sb.append("{\"MediaId\":\"").append(videoIds[i]).append("\"}");
            if (i < videoIds.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]}]}");
        String timeLine = sb.toString();
        System.out.println("timeLine:" + timeLine);
        request.setTimeline(timeLine);
        // 设置合成的新媒体标题
        request.setTitle(getTitle(videoIds));
        // 多段合成一个视频
        if (type == 0 || type == 1) {
            request.setProduceConfig("{\"ContainerFormat\":\"M3U8\"}");
        }
        // 多段剪切视频拼接
        if (type == 2) {
            request.setProduceConfig("{\"ContainerFormat\":\"M3U8\",\"ProcessMode\":\"Joint\"}");
        }

        ProduceEditingProjectVideoResponse response = client.getAcsResponse(request);
        System.out.println(response.getMediaId());
        // 入库
        insert(new VideoInfoDO(response.getMediaId(), request.getTitle()));
    }

    /**
     * 音频和视频合并剪切
     */
    public static void videoAudioTracks(String videoId, String audio1, String audio2, DefaultAcsClient client) throws Exception {
        // Timeline
        StringBuilder sb = new StringBuilder();
        // 要拼接的视频
        sb.append("{\"VideoTracks\":[{\"VideoTrackClips\": [");
        sb.append("{\"MediaId\":\"").append(videoId).append("\"");
        //.append(",\"Effects\":[{\"Type\":\"Volume\",\"Gain\":\"0\"}]");
        sb.append("}]}]");
        // 加上两段音频
        sb.append(",\"AudioTracks\":[{\"AudioTrackClips\":[")
            .append("{\"MediaId\":\"").append(audio1).append("\", \"TimelineIn\":0")
            //.append(",\"Effects\":[{\"Type\":\"Volume\",\"Gain\":\"1\"}]")
            .append("}]}")
            .append(",{\"AudioTrackClips\":[")
            .append("{\"MediaId\":\"").append(audio2).append("\", \"TimelineIn\":5")
            //.append(",\"Effects\":[{\"Type\":\"Volume\",\"Gain\":\"1\"}]")
            .append("}]}]}");
        String track = sb.toString();
        System.out.println(track);
        ProduceEditingProjectVideoRequest request = new ProduceEditingProjectVideoRequest();
        request.setTimeline(track);
        request.setTitle(getTitle(videoId, audio1, audio2));
        request.setProduceConfig("{\"ContainerFormat\":\"M3U8\"}");
        ProduceEditingProjectVideoResponse response = client.getAcsResponse(request);
        System.out.println(response.getMediaId());

        insert(new VideoInfoDO(response.getMediaId(), request.getTitle()));
    }

    public static String getTitle(String... mediaIds) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (String mediaId : mediaIds) {
            String title = getTitleByMediaId(mediaId);
            if (null == title) {
                GetPlayInfoRequest request = new GetPlayInfoRequest();
                request.setVideoId(mediaId);
                GetPlayInfoResponse response = VodUtils.FC_CLIENT.getAcsResponse(request);
                title = response.getVideoBase().getTitle();
            }
            sb.append(title);
        }
        return sb.toString();
    }

    /**
     * 保存到数据库
     *
     * @param videoInfoDO
     */
    public static void insert(VideoInfoDO videoInfoDO) {
        Connection conn = JdbcUtils.getConnection();
        PreparedStatement ps = null;
        String sql = "insert into video_info(media_id, title, play_url, create_time) values(?, ?, ?, ?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, videoInfoDO.getMediaId());
            ps.setString(2, videoInfoDO.getTitle());
            ps.setString(3, videoInfoDO.getPlayUrl());
            ps.setDate(4, new Date(new java.util.Date().getTime()));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != ps) {
                    ps.close();
                }
                if (null != conn) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据mediaId查询标题
     *
     * @param mediaId
     * @return
     */
    public static String getTitleByMediaId(String mediaId) {
        Connection conn = JdbcUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select title from video_info where media_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, mediaId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("title");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != rs) {
                    rs.close();
                }
                if (null != ps) {
                    ps.close();
                }
                if (null != conn) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
