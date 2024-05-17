package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

//配置类,用于管理第三方的jar包
@Configuration
public class AlphaConfig {
    //定义第三方的bean,下面方法返回的对象将被装配到容器中,Bean的名字即为simpleDateFormat
    @Bean
    public SimpleDateFormat simpleDateFormat (){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

}
