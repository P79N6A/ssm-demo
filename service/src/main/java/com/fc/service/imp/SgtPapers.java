package com.fc.service.imp;

import com.fc.service.CompactDisc;
import org.springframework.stereotype.Component;

/**
 * 添加CD接口的一个实现,Spring4 bean注入
 * Component注解表明该类会作为组件类，并告诉spring为这个类创建bean
 * @author SYSTEM on 2017/10/24.
 */
//@Service(value = "compactDisc")
@Component
public class SgtPapers implements CompactDisc {

    @Override
    public void play() {
        String title = "sgt paper's lonely heart club band";
        String artist = "The beatles";
        System.out.println("playing " + title + " by " + artist);
    }
}
