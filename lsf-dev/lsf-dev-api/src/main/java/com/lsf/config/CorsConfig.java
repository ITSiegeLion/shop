package com.lsf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    public CorsConfig(){}

    /**
     * 配置跨域信息
     * @return
     */
    @Bean
    public CorsFilter corsFilter(){
        //1.添加cors配置信息
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:8080");
        //设置是否发送Cookie
        configuration.setAllowCredentials(true);
        //设置允许请求的方式
        configuration.addAllowedMethod("*");
        //设置允许的header
        configuration.addAllowedHeader("*");
        //为url添加映射路径
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", configuration);
        //返回定义号的corsSource
        return new CorsFilter(corsSource) ;
    }
}
