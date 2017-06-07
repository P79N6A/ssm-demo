package com.fc.enums;

/**
 * Created by fangcong on 2017/4/11.
 */
public enum MySchedule {

    MODAY {
        @Override
        String plan() {
            return "play";
        }
    },
    THUESDAY {
        @Override
        String plan() {
            return "play";
        }
    },
    WEDNESDAY {
        @Override
        String plan() {
            return "learn";
        }
    };

    abstract String plan();
}
