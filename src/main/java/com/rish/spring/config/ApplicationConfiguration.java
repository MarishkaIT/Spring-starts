package com.rish.spring.config;

import com.rish.spring.database.pool.ConnectionPool;
import com.rish.spring.database.repository.UserRepository;
import com.rish.web.config.WebConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;

@Import(WebConfiguration.class)
@Configuration(proxyBeanMethods = true)
//@ImportResource("classpath:application.xml")
public class ApplicationConfiguration {

    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public ConnectionPool pool2(@Value("${db.username}") String username){
        return new ConnectionPool(username, 20);
    }

    @Bean
    public ConnectionPool pool3(){
        return new ConnectionPool("test-pool", 25);
    }


}
