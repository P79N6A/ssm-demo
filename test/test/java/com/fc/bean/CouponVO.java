package com.fc.bean;

/**
 * @author fangcong on 2017/3/22.
 */
public class CouponVO {

    /**
     * 商家id
     */
    private Long sellerId;
    /**
     * 优惠券id
     */
    private String activityId;

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @Override
    public String toString() {
        return "CouponVO{" +
            "sellerId=" + sellerId +
            ", activityId='" + activityId + '\'' +
            '}';
    }
}
