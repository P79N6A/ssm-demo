package com.fc.bean;

/**
 * @author fangcong on 2017/6/9.
 */
public class JsonStr {

    /**
     * userNick : b***hong2005
     * prizeName : 机洗绝配礼包
     * createTime : 2015-06-25 10:41:35.0
     * prizeType : 0
     * id : 262
     * prizeId : 1913056
     */
    private String userNick;
    private String prizeName;
    private String createTime;
    private int prizeType;
    private int id;
    private int prizeId;

    public void setUserNick(String userNick) { this.userNick = userNick;}

    public void setPrizeName(String prizeName) { this.prizeName = prizeName;}

    public void setCreateTime(String createTime) { this.createTime = createTime;}

    public void setPrizeType(int prizeType) { this.prizeType = prizeType;}

    public void setId(int id) { this.id = id;}

    public void setPrizeId(int prizeId) { this.prizeId = prizeId;}

    public String getUserNick() { return userNick;}

    public String getPrizeName() { return prizeName;}

    public String getCreateTime() { return createTime;}

    public int getPrizeType() { return prizeType;}

    public int getId() { return id;}

    public int getPrizeId() { return prizeId;}
}
