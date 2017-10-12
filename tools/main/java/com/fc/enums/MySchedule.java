package com.fc.enums;

/**
 * Created by fangcong on 2017/4/11.
 */
public enum MySchedule {

    /**
     * 星期一
     */
    MODAY {
        @Override
        String plan() {
            return "play";
        }
    },
    /**
     * 星期二
     */
    THUESDAY {
        @Override
        String plan() {
            return "play";
        }
    },
    /**
     * 星期三
     */
    WEDNESDAY {
        @Override
        String plan() {
            return "learn";
        }
    };

    abstract String plan();
}
