package com.metacoding.planitapiserver._core.config;

import com.metacoding.planitapiserver._core.filter.CorsFilter;
import com.metacoding.planitapiserver._core.filter.JwtAuthorizationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FilterConfig {

    @Bean
    FilterRegistrationBean<JwtAuthorizationFilter> jwtAuthorizationFilter() {
        System.out.println("JwtAuthorizationFilter scan");
        FilterRegistrationBean<JwtAuthorizationFilter> bean =
                new FilterRegistrationBean<>(new JwtAuthorizationFilter());
        bean.addUrlPatterns("/api/*");
        bean.setOrder(1);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        System.out.println("corsFilter scan");
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter());
        bean.addUrlPatterns("/*");
        bean.setOrder(0); // 낮은 번호부터 실행됨.
        return bean;
    }

}
