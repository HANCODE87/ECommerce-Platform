package com.i4.ecommerce_web.config;

import com.i4.ecommerce_web.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //配置類
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor)
                .addPathPatterns("/**") // 攔截所有請求
                .excludePathPatterns("/login", "/error"); // 排除某些請求
    }

    // 添加 CORS 配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 允許所有的 API 路徑
                .allowedOrigins("http://localhost:3000")  // 允許來自 React 前端的請求
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // 允許的 HTTP 方法
                .allowedHeaders("*")  // 允許所有請求頭
                .allowCredentials(true);  // 允許攜帶憑證 (例如 Cookie)
    }

}
