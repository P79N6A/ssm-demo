package com.fc.bean;

import java.io.Serializable;

/**
 * @author fangcong
 */
public class FormInfo implements Serializable {

    private static final long serialVersionUID = 4332272293993546339L;
    private long id;
    private long infoId;
    private String app;
    private String userNick;
    private String dataKey;
    private String dataValue;

    public FormInfo() {
    }

    public FormInfo(long infoId, String userNick, String dataKey, String dataValue) {
        this.infoId = infoId;
        this.userNick = userNick;
        this.dataKey = dataKey;
        this.dataValue = dataValue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getInfoId() {
        return infoId;
    }

    public void setInfoId(long infoId) {
        this.infoId = infoId;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }


}
