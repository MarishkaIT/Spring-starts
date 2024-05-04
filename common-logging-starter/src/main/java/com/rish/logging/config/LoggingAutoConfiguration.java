package com.rish.logging.config;

import com.rish.logging.aop.CommonPointcuts;
import com.rish.logging.aop.FirstAspect;
import com.rish.logging.aop.SecondAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
@EnableConfigurationProperties(LoggingProperties.class)
@ConditionalOnClass(LoggingProperties.class)
@ConditionalOnProperty(prefix = "app,common.logging", name = "enabled", havingValue = "true")
public class LoggingAutoConfiguration {

    @PostConstruct
    void init() {
        log.info("LoggingAutoConfiguration initialized");
    }

    @Bean
    public CommonPointcuts commonPointcuts(){
        return new CommonPointcuts();
    }

    @Bean
    @Order(1)
    public FirstAspect firstAspect(){
        return new FirstAspect();
    }

    @Bean
    @Order(2)
    public SecondAspect secondAspect(){
        return new SecondAspect();
    }
}
