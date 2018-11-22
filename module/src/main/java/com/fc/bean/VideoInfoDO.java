package com.fc.bean;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author fangcong on 2018/11/21.
 */
@Getter
@Setter
@NoArgsConstructor
public class VideoInfoDO {

    private Long id;

    /**
     * 播放id
     */
    private String mediaId;

    /**
     * 媒体标题
     */
    private String title;

    /**
     * 播放地址
     */
    private String playUrl;

    /**
     * 创建时间
     */
    private Date createTime;

    public VideoInfoDO(String mediaId, String title) {
        this.mediaId = mediaId;
        this.title = title;
    }
}
