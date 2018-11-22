package com.zip;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

/**
 * AK账号信息，拥有VOD读写权限
 *
 * @author fangcong on 2018/11/15.
 */
public interface VodUtils {

    /**
     * youkubeijing账号
     */
    String YK_ACCESS_KEY = "LTAIeKQLOR3DmfNG";

    String YK_ACCESS_SECRET = "I4OcW1KPyWxQjsRCyefc40lbwQuYie";

    /**
     * 上传的模板ID
     */
    String YK_TEMPALTE_ID = "4b8dbedab41487d129cb14334ca39085";

    /**
     * 私人账号
     */
    String fc_key = "LTAIRcXbJQc8YM6d";

    String fc_key_secret = "cztAKrJBPlZXZqsmIwoSLF5sJ0rEyT";

    String fc_template_id = "6719e14a2b5e72f7c99d042dac1c49b8";

    DefaultAcsClient FC_CLIENT = new DefaultAcsClient(
        DefaultProfile.getProfile("cn-shanghai", fc_key, fc_key_secret)
    );

    /**
     * 初始化客户端
     */
    DefaultAcsClient YK_CLIENT = new DefaultAcsClient(
        DefaultProfile.getProfile("cn-shanghai", YK_ACCESS_KEY, YK_ACCESS_SECRET)
    );
}
