package com.fc.resolver;

import com.fc.common.AjaxResult;
import com.fc.enums.ErrorCodeEnum;
import com.fc.common.JsonUtil;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import com.fc.exception.IllegalArgumentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by fangcong on 2016/12/8.
 */
public class ErrorHandlerExceptionResolver extends AbstractHandlerExceptionResolver {

    private String errorPage;

    public String getErrorPage() {
        return errorPage;
    }

    public void setErrorPage(String errorPage) {
        this.errorPage = errorPage;
    }

    /**
     * 全局异常处理类
     * 如果是浏览器访问的，出现异常全部跳转到错误页面，如果是rest api接口访问出现异常返回json串
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @return
     */
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try{
            String uri = request.getRequestURI();
            String errorCode = "";
            String msg = "";
            //异常处理
            if (ex instanceof IllegalArgumentException || ex instanceof com.fc.exception.IllegalArgumentException){
                errorCode = ErrorCodeEnum.PARAM_ERROR.getMessage();
                msg = ex.getMessage();
                logger.warn("Catch Exception:" + uri, ex);
            }

            String ajax_flag = request.getHeader("X-Requested-With");
            ResponseBody responseBody = null;
            if (handler instanceof HandlerMethod){
                /**获取response body，由于response body标注的handler，
                 * 不会结果视图进行解析，而是由messageconverter进行处理
                 **/
                responseBody = ((HandlerMethod)handler).getMethodAnnotation(ResponseBody.class);
            }
            String ajaxFlag = "xmlhttprequest";
            if (responseBody != null || ajaxFlag.equalsIgnoreCase(ajax_flag)){
                try {
                    JsonUtil.printJsonOrJsonp(request, response, AjaxResult.getFailResult(errorCode, msg));
                } catch (Exception e) {
                    logger.error("error response", e);
                }

                return new ModelAndView();
            }
            ModelMap model = new ModelMap();
            model.addAttribute("detailMsg", errorCode + "系统异常");
            return new ModelAndView(errorPage, model);
        } catch (Exception e){
            logger.error("handling of [" + ex.getClass().getName() + " ] resulted in Exception",e);
        }
        return new ModelAndView(errorPage);
    }
}
