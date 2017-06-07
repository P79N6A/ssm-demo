package com.fc.service.imp;

import com.fc.service.RegistryServiceConsumer;
import org.springframework.stereotype.Service;

/**
 * @author fangcong on 2017/5/2.
 */
@Service
public class RegistryServiceConsumerImp implements RegistryServiceConsumer {

   /* @Resource
    private TestRegistryService testRegistryService;*/

    @Override
    public String sayHello(String words) {
        /*return testRegistryService.hello(words);*/
        return "xxx";
    }
}
