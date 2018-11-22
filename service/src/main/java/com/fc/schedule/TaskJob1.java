package com.fc.schedule;

import com.fc.common.DateUtils;
import org.springframework.stereotype.Service;

/**
 * @author fangcong on 2018/11/22.
 */
@Service
public class TaskJob1 {

    public void run() {
        System.out.println("schedule task job1 run at " + DateUtils.getCurrDateTimeStr());
    }
}
