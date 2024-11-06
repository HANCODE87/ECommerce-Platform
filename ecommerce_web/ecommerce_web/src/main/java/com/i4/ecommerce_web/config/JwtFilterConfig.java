package com.i4.ecommerce_web.config;

import com.i4.ecommerce_web.filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtFilterConfig {
//    @Bean
    public FilterRegistrationBean<JwtFilter> registerJwtFilter(){
        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtFilter()); //將 JwtFilter 過濾器實例分配給 registrationBean
        registrationBean.addUrlPatterns("/api/*"); //指定攔截url路徑
        registrationBean.setOrder(1); // 設定過濾器的優先順序
        return registrationBean;
    }
}
