package com.fc.controller;

import javax.annotation.Resource;

import com.fc.common.AjaxResult;
import com.fc.service.RegistryServiceConsumer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author fangcong on 2017/5/2.
 */
@Controller
@RequestMapping(value = "/consumer")
public class DubboConsumerController {

    @Resource
    private RegistryServiceConsumer registryServiceConsumer;

    @RequestMapping(value = "/say.json")
    @ResponseBody
    public AjaxResult sayHello(String key) {
        String result = registryServiceConsumer.sayHello(key);
        return AjaxResult.getSuccessResult(result);
    }
}
