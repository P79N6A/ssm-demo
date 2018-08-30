package com.exams;

import java.io.Serializable;

import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractBy.Type;

/**
 * @author fangcong on 2018/8/23.
 */
public class WeiBoRepo implements Serializable {

    @ExtractBy(value = "img[src]", type = Type.Css)
    private String picture;

    public WeiBoRepo() {
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "WeiBoRepo{picture='" + picture + '\'' + '}';
    }
}
