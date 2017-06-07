package test.test.bean;

import java.io.Serializable;

/**
 * Created by fangcong on 2016/9/21.
 */
public class BonusModule implements Serializable{


    private long id;
    private long storeId;
    private String storeName;
    private long sellerId;
    private String activityId;
    private String storeUrl;
    private int prize;

    public BonusModule(long id, long storeId, String storeName, long sellerId, String activityId, String storeUrl, int prize) {
        this.id = id;
        this.storeId = storeId;
        this.storeName = storeName;
        this.sellerId = sellerId;
        this.activityId = activityId;
        this.storeUrl = storeUrl;
        this.prize = prize;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getStoreUrl() {
        return storeUrl;
    }

    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }
}
