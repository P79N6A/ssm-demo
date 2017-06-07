package com.fc.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by fangcong on 2016/12/1.
 */
public class MyInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(MyInterceptor.class);
    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<>("StopWatch-StartTime");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("My Interceptor begin ...");
        long startTime = System.currentTimeMillis();
        startTimeThreadLocal.set(startTime);
        String requestUri = request.getRequestURI();
        String requestPath = request.getContextPath();
        String url = requestUri.substring(requestPath.length());
        logger.debug(request.getMethod() + " method:" + url);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.debug("postHandler ...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long endTime = System.currentTimeMillis();
        long useTime = endTime - startTimeThreadLocal.get();
        logger.debug("My Interceptor end and use time :" + useTime);
    }
}
