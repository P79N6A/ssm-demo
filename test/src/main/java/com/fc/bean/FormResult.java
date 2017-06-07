package com.fc.bean;

import java.io.Serializable;
import java.util.Map;

public class FormResult implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2514286363304242540L;
    private long infoId;
    private String userNick;
    private Map<String, Object> infos;

    public long getInfoId() {
        return infoId;
    }

    public void setInfoId(long infoId) {
        this.infoId = infoId;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }


    public Map<String, Object> getInfos() {
        return infos;
    }

    public void setInfos(Map<String, Object> infos) {
        this.infos = infos;
    }

    @Override
    public String toString() {
        return "FormResult{" +
                "infoId=" + infoId +
                ", userNick='" + userNick + '\'' +
                ", infos=" + infos +
                '}';
    }
}
