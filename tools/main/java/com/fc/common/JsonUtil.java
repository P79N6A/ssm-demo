package com.fc.common;

import com.alibaba.fastjson.JSON;
import com.taobao.security.util.SecurityUtil;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonUtil {

    public static void printJsonOrJsonp(HttpServletRequest request, HttpServletResponse response, Object result) throws IOException {

        String jsonpCallBack = request.getParameter("callback");
        String securityStr = JSON.toJSONString(result);
        //将tab换成空格
        if (StringUtils.isNotBlank(securityStr)) {
            securityStr = securityStr.replaceAll("\t", " ");
        }
        if (jsonpCallBack != null) {
            jsonpCallBack = SecurityUtil.escapeHtml(jsonpCallBack);
            StringBuilder sb = new StringBuilder(jsonpCallBack).append("(").append(securityStr).append(")");
            securityStr = sb.toString();
        }
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(securityStr);
    }

}
