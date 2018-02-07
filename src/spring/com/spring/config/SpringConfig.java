package com.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages={"com.spring"})  //×Ô¶¯¼ÓÔØ
@Import(SchoolConfig.class)
@ImportResource("classpath:spring/spring-school.xml")
public class SpringConfig {

}
