package com.zip;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Stream;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.*;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse.PlayInfo;
import com.fc.bean.VideoInfoDO;
import com.fc.common.JdbcUtils;

/**
 * 根据videoId获取播放地址
 *
 * @author fangcong on 2018/11/15.
 */
public class PalyVideo {

    private static final String[] PROVINCES = {
            "HL", "JL", "LN", "BJ", "SD", "SH", "FJ", "TW", "HA", "JX",
            "HN", "CQ", "SC", "GD", "HK", "TJ", "HE", "NM", "SX", "SN",
            "NX", "GS", "QH", "XJ", "JS", "ZJ", "AH", "HB", "GZ", "XZ",
            "YN", "MO", "HI", "GX"
    };

    private static final String[] INTERESTS = {
            "A", "D", "E", "F", "L", "M", "N", "R", "S"
    };

    private static final String[] DEFULT_GS = {"G1", "G2", "G3"};

    /**
     * VO_1_DONGYU,VO_1_YANG,VO_1_HAORAN,VO_1_YISHANG
     */
    private static final Map<String, String> VOS_1_IDS;

    private static final Map<String, String> VOS_2_IDS;

    private static final Map<String, String> VOS_3_IDS;

    private static final Map<String, String> VOS_7_IDS;

    static {
        VOS_1_IDS = new HashMap<>();
        VOS_1_IDS.put("DONGYU", "");
        VOS_1_IDS.put("YISHAN", "");
        VOS_1_IDS.put("YANG", "");
        VOS_1_IDS.put("HAORAN", "");

        VOS_2_IDS = new HashMap<>();
        VOS_2_IDS.put("YISHAN", "");
        VOS_2_IDS.put("YANG", "");
        VOS_2_IDS.put("HAORAN", "");

        VOS_3_IDS = new HashMap<>();
        VOS_3_IDS.put("DONGYU", "");
        VOS_3_IDS.put("YISHAN", "");
        VOS_3_IDS.put("YANG", "");
        VOS_3_IDS.put("HAORAN", "");

        VOS_7_IDS = new HashMap<>();
        VOS_7_IDS.put("DONGYU", "");
        VOS_7_IDS.put("YISHAN", "");
        VOS_7_IDS.put("YANG", "");
        VOS_7_IDS.put("HAORAN", "");
    }

    public static void main(String[] args) throws Exception {
        batchTrackVideo(0);
        // 1.上传音频和视频
        //testUploadVideo("audio_10", "F:/test/vedio/audio_10.mp4");
        // 2.合成视频或者合成音频
        //heChengVideos(1, VodUtils.FC_CLIENT, "65b5303cef644d38a95c27e618046397", "04ee35dc0dfd41c3be4ccba1ba95f1e4");
        /*heChengVideos(2, VodUtils.YK_CLIENT,
                "71bcb8639a064dffa6a3eb62e67abe23", "97420502f2df409d92fcb0b0cf668fea",
                 "d49feba85dd54e78a099b335ab4b00c2", "ada99c422e00409887c1cf6d62626492",
                "7e859da0435946cc91d0e84d53a9fd65", "9f118f85536e4e038fc15204120ae472",
                "b62193ad410a4afe90fb616020481369");*/
        //getPalyAuth("dbfa4a1e30644ff1b6e44f9767e4f4e1");
        // 3.音视频混合合成
        //videoAudioTracks("428c35c13c534a1b81457b7ed1b62f97", "443687a4e3e3462e82cb0cc951aaeded", "b8f6b0ad8ac849849a5c79d4192a17fd", VodUtils.YK_CLIENT);
        //getPlayInfo("366e0483703749f692025f00cec8c8e8", "", VodUtils.YK_CLIENT);
    }

    /**
     * 合成Video1-video7中的视频与音频
     *
     * @param index 1-7
     * @throws Exception
     */
    public static void batchTrackVideo(int index) {
        // 34 x 4 = 132
        if (index == 1) {
            Stream.of(PROVINCES).forEach(province -> {
                String videoTitle = "VIDEO_1_" + province;
                String mediaId = getMediaIdByTitle(videoTitle);

                String audio1Title = "SFX_1_" + province;
                String audioId1 = getMediaIdByTitle(audio1Title);
                // 每个省份视频 + 4段明星音频
                VOS_1_IDS.entrySet().stream().forEach(entry -> {
                    String title = videoTitle;
                    try {
                        if ("DONGYU".equals(entry.getKey())) {
                            title = title + "F" + entry.getKey();
                            videoAudioTracks(title, mediaId, audioId1, entry.getValue());
                        } else {
                            title = title + "M" + entry.getKey();
                            videoAudioTracks(title, mediaId, audioId1, entry.getValue());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            });
        }

        // 2 + 2 x 3 = 8
        if (index == 2) {
            Stream.of("F_80", "F_90", "M_80", "M_90").forEach(
                    str -> {
                        String mediaId = getMediaIdByTitle("VIDEO_2_" + str);
                        String audioId1 = getMediaIdByTitle("SFX_2_" + str);
                        if ("F_80".equals(str) || "F_90".equals(str)) {
                            try {
                                videoAudioTracks("VIDEO_2_" + str + "DONGYU", mediaId, audioId1, "");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            // 4段明星音频
                            VOS_2_IDS.entrySet().stream().forEach(entry -> {
                                String title = "VIDEO_2_" + str + entry.getKey();
                                try {
                                    videoAudioTracks(title, mediaId, audioId1, entry.getValue());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                    }
            );
        }

        // 34 x 1 + 34 * 3
        if (index == 3) {
            Stream.of(PROVINCES).forEach(province -> {
                // 每个省份视频 + 4段明星音频
                VOS_3_IDS.entrySet().stream().forEach(entry -> {
                    try {
                        if ("DONGYU".equals(entry.getKey())) {
                            String videoTitle = "VIDEO_3_" + "F_" + province;
                            String mediaId = getMediaIdByTitle(videoTitle);

                            String audio1Title = "SFX_3_" + "F_" + province;
                            String audioId1 = getMediaIdByTitle(audio1Title);

                            String title = "VIDEO_3" + province + "F" + entry.getKey();
                            videoAudioTracks(title, mediaId, audioId1, entry.getValue());
                        } else {
                            String videoTitle = "VIDEO_3_" + "M_" + province;
                            String mediaId = getMediaIdByTitle(videoTitle);

                            String audio1Title = "SFX_3_" + "M_" + province;
                            String audioId1 = getMediaIdByTitle(audio1Title);

                            String title = "VIDEO_3" + province + "M" + entry.getKey();
                            videoAudioTracks(title, mediaId, audioId1, entry.getValue());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            });
        }

        // 34 x 4
        if (index == 7) {
            Stream.of(PROVINCES).forEach(province -> {
                VOS_7_IDS.entrySet().stream().forEach(entry -> {
                    try {
                        if ("DONGYU".equals(entry.getKey())) {
                            for (int i = 0; i < 2; i++) {
                                String age;
                                if (i == 0) {
                                    age = "80";
                                } else {
                                    age = "90";
                                }
                                String videoTitle = "VIDEO_7_F_" + age;
                                String mediaId = getMediaIdByTitle(videoTitle);

                                String audio1Title = "SFX_7_F_" + province;
                                String audioId1 = getMediaIdByTitle(audio1Title);

                                String title = "VIDEO_3" + province + "F" + age + entry.getKey();
                                videoAudioTracks(title, mediaId, audioId1, entry.getValue());
                            }
                        } else {
                            for (int i = 0; i < 2; i++) {
                                String age;
                                if (i == 0) {
                                    age = "80";
                                } else {
                                    age = "90";
                                }
                                String videoTitle = "VIDEO_7_M_" + age;
                                String mediaId = getMediaIdByTitle(videoTitle);

                                String audio1Title = "SFX_7_M_" + province;
                                String audioId1 = getMediaIdByTitle(audio1Title);

                                String title = "VIDEO_3" + province + "M" + age + entry.getKey();
                                videoAudioTracks(title, mediaId, audioId1, entry.getValue());
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            });
        }

        if (index >= 4 && index <= 6) {
            tarck4To6Video(index);
        }

        if (index == 0) {
            trackG1ToG3();
        }
    }

    private static void tarck4To6Video(int index) {
        Stream.of(INTERESTS).forEach(interest -> {
            Stream.of("F", "M").forEach(gender -> {
                String mediaTitle = "VIDEO_" + index + "_" + gender + "_" + interest;
                String mediaId = getMediaIdByTitle(mediaTitle);

                String audioTitle = "SFX_" + index + "_" + gender + "_" + interest;
                String audio1 = getMediaIdByTitle(audioTitle);

                try {
                    if ("F".equals(gender)) {
                        String voTitle = "VO_" + index + "_" + "DONGYU";
                        String audio2 = getMediaIdByTitle(voTitle);
                        String title = "VIDEO_" + index + "_F" + interest + "DONGYU";
                        videoAudioTracks(title, mediaId, audio1, audio2);
                    } else {
                        Stream.of("YISHAN", "HAORAN", "YANG").forEach(vo -> {
                            String voTitle = "VO_" + index + "_" + vo;
                            String audio2 = getMediaIdByTitle(voTitle);
                            String title = "VIDEO_" + index + "_M" + interest + vo;
                            try {
                                videoAudioTracks(title, mediaId, audio1, audio2);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });
    }

    private static void trackG1ToG3() {
        Stream.of("DONGYU", "YISHAN", "HAORAN", "YANG").forEach(vo -> {
            if ("DONGYU".equals(vo)) {
                Stream.of(4, 5, 6).forEach(i -> {
                    String g = DEFULT_GS[6 - i];
                    String mediaTitle = "VIDEO_" + i + "_F_" + g;
                    String mediaId = getMediaIdByTitle(mediaTitle);

                    String audioId1 = getMediaIdByTitle("SFX_" + i + "_F_" + g);
                    String audioId2 = getTitleByMediaId("VO_" + i + "_" + g + "_DONGYU");

                    String title = "VIDEO_" + i + "F" + g + vo;
                    try {
                        videoAudioTracks(title, mediaId, audioId1, audioId2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } else {
                Stream.of(4, 5, 6).forEach(i -> {
                    String g = DEFULT_GS[6 - i];
                    String mediaTitle = "VIDEO_" + i + "_M_" + g;
                    String mediaId = getMediaIdByTitle(mediaTitle);

                    String audioId1 = getMediaIdByTitle("SFX_" + i + "_M_" + g);
                    String audioId2 = getTitleByMediaId("VO_" + i + "_" + g + "_DONGYU");

                    String title = "VIDEO_" + i + "M" + g + vo;
                    try {
                        videoAudioTracks(title, mediaId, audioId1, audioId2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }

    /**
     * 查询播放地址
     *
     * @param mediaId id
     * @param format  格式
     * @param client  客户端
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
        System.out.println(response.getVideoBase().getDuration());
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
            sb.append("{\"MediaId\":\"").append(videoIds[i]).append("\"");
            if (i == 0 || i % 2 == 0) {
                sb.append(",\"Effects\": [{\"Color\": \"#000000\", \"Duration\": 0.5, \"SubType\": \"Out\", \"Type\": \"Fade\"}]}");
            } else {
                sb.append(",\"Effects\": [{\"Color\": \"#000000\", \"Duration\": 0.5, \"SubType\": \"In\", \"Type\": \"Fade\"}]}");
            }
            if (i < videoIds.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]}]}");
        String timeLine = sb.toString();
        System.out.println("timeLine:" + timeLine);
        request.setTimeline(timeLine);
        // 设置合成的新媒体标题
        String title = getTitle(videoIds);
        request.setTitle(title + "_track");
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
    public static void videoAudioTracks(String title, String videoId, String audio1, String audio2) throws Exception {
        // Timeline
        System.out.println("{title:" + title);
        /**StringBuilder sb = new StringBuilder();
         // 要拼接的视频
         sb.append("{\"VideoTracks\":[{\"VideoTrackClips\": [");
         sb.append("{\"MediaId\":\"").append(videoId).append("\",\"Out\":5");
         //sb.append(",\"Effects\": [{\"Color\": \"#000000\", \"Duration\": 0.2, \"SubType\": \"In\", \"Type\": \"Fade\"}]");
         //sb.append(",\"Effects\": [{\"Color\": \"#000000\", \"Duration\": 0.2, \"SubType\": \"Out\", \"Type\": \"Fade\"}]");
         sb.append("}]}]");
         // 加上两段音频
         sb.append(",\"AudioTracks\":[{\"AudioTrackClips\":[")
         .append("{\"MediaId\":\"").append(audio1).append("\", \"Out\":5")
         //.append(",\"Effects\":[{\"Type\":\"Volume\",\"Gain\":\"1\"}]")
         .append("}]}")
         .append(",{\"AudioTrackClips\":[")
         .append("{\"MediaId\":\"").append(audio2).append("\", \"Out\":5")
         //.append(",\"Effects\":[{\"Type\":\"Volume\",\"Gain\":\"1\"}]")
         .append("}]}]}");
         String track = sb.toString();
         System.out.println(track);
         ProduceEditingProjectVideoRequest request = new ProduceEditingProjectVideoRequest();
         request.setTimeline(track);
         request.setTitle(title);
         request.setProduceConfig("{\"ContainerFormat\":\"M3U8\"}");
         ProduceEditingProjectVideoResponse response = VodUtils.YK_CLIENT.getAcsResponse(request);
         System.out.println(response.getMediaId());

         insert(new VideoInfoDO(response.getMediaId(), request.getTitle()));*/
    }

    public static String getTitle(String... mediaIds) throws Exception {
        if (mediaIds.length > 3) {
            return "final_track_video";
        }
        StringBuilder sb = new StringBuilder();
        for (String mediaId : mediaIds) {
            String title = getTitleByMediaId(mediaId);
            if (null == title) {
                GetPlayInfoRequest request = new GetPlayInfoRequest();
                request.setVideoId(mediaId);
                GetPlayInfoResponse response = VodUtils.YK_CLIENT.getAcsResponse(request);
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

    public static String getMediaIdByTitle(String title) {
        Connection conn = JdbcUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select media_id from video_info where title = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("media_id");
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
        //System.err.println("not foud mediaId by : " + title);
        return null;
    }

    public static void getPalyAuth(String videoId) throws Exception {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoId);
        GetVideoPlayAuthResponse response = VodUtils.YK_CLIENT.getAcsResponse(request);
        //播放凭证
        System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
        //VideoMeta信息
        System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
    }
}
