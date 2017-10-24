package com.fc.service.imp;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 组件扫描默认是不启动的，ComponentScan启动组件扫描，扫描同路径包下的类
 *
 * @author SYSTEM on 2017/10/24.
 */
@Configuration
@ComponentScan
public class CdPlayerConfig {
}
