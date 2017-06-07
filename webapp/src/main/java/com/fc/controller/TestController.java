package com.fc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.fc.bean.User;
import com.fc.common.AjaxResult;
import com.fc.common.Asserts;
import com.fc.resolver.JsonParam;
import com.fc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fangcong on 2016/11/29.
 */
@Controller
public class TestController {

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Resource
    private UserService userService;

    /**
     * 参数绑定的定制，Spring4.2提供了新的实现(一般用于日期格式的定制)
     * ########################4.2中实现#############################
     * binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
     * #############################################################
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 根据姓名查询用户信息
     * @param name
     * @return
     */
    @RequestMapping(value = "/test/query.json",method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult queryInfo(String name){
        logger.debug("/test/query.json begin ...");
        List<User> list = userService.queryUser(name);
        if (list.size() > 0){
            return AjaxResult.getSuccessResult(list);
        }
        return AjaxResult.getFailResult("E001","no data found");
    }

    /**
     * 添加一条记录
     * @param user
     * @return
     */
    @RequestMapping(value = "/test/addUser.json",method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult addUser(@JsonParam User user){
        Asserts.allCanNotFalse("记录不能为空",user != null,user.getRealName() != null,user.getLoginName() != null);
        if (user.getSex() == null){
            user.setSex('M');
        }
        user = userService.addUser(user);
        return AjaxResult.getSuccessResult(user.getId());
    }

    @RequestMapping(value = "/test/login.json",method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult login(String userName, String passWord) {
        User user = new User();
        user.setLoginName(userName);
        user.setLoginPassword(passWord);
        if (userService.existUser(user)) {
            return AjaxResult.getSuccessResult();
        }
        return AjaxResult.getFailResult("E01", "用户不存在");
    }

    /**
     * 添加一条记录
     * @param user
     * @return
     */
    @RequestMapping(value = "/test/addUserNew.json",method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult addUserNew(User user){
        Asserts.allCanNotFalse("记录不能为空",user != null,user.getRealName() != null,user.getLoginName() != null);
        if (user.getSex() == null){
            user.setSex('M');
        }
        user = userService.addUser(user);
        return AjaxResult.getSuccessResult(user.getId());
    }

    /**
       * 根据登陆名查询用户信息，测试ehcache缓存
    * @param loginName
    * @return
            */
    @RequestMapping(value = "/test/queryLogin.json",method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult queryInfoByLoginName(String loginName){
        Asserts.allCanNotNull("参数不能为空",loginName);
        User user = userService.queryByLoginName(loginName);
        return AjaxResult.getSuccessResult(user);
    }

    /**
     * 测试跳转到默认errorPage:404.jsp
     */
    @RequestMapping(value = "/test/error.json", method = RequestMethod.GET)
    @ResponseBody
    public void testErrorPage(){
        logger.info("不返回responseBody");
    }

    @RequestMapping(value = "test/getRequestInfo.json", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult testHttpRequest(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        String url = request.getRequestURL().toString();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String remoteAddr = request.getRemoteAddr();
        String remoteHost = request.getRemoteHost();
        int remotePort = request.getRemotePort();
        String user = request.getRemoteUser();
        String localAddr = request.getLocalAddr();
        String localName = request.getLocalName();
        int localPort = request.getLocalPort();
        Map paramMap = request.getParameterMap();
        Enumeration<String> enumHeader = request.getHeaderNames();
        map.put("url", url);
        map.put("uri", uri);
        map.put("queryString", queryString);
        map.put("remoteAddr", remoteAddr);
        map.put("remoteHost", remoteHost);
        map.put("remotePort", remotePort);
        map.put("user", user);
        map.put("localAddr", localAddr);
        map.put("localName", localName);
        map.put("localPort", localPort);
        /*map.put("paramMap", paramMap);
        map.put("enumHeader", enumHeader);*/
        return AjaxResult.getSuccessResult(map);
    }

    @RequestMapping(value = "/{word}/say.json", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult testPathVariable(@PathVariable("word") String word) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("word", word);
        return AjaxResult.getSuccessResult(map);
    }
}
