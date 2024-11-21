package com.i4.ecommerce_web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.i4.ecommerce_web.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //若回傳的方法是OPTIONS，則直接放行
        if (request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
            return;
        }


        //排除登入或註冊
        if (request.getServletPath().equals("/api/user/login") || request.getServletPath().equals("/api/user/register")) {
        filterChain.doFilter(request, response);
        return;
        }

        // 排除 GET 請求所有以 /api/products/開頭 的路徑
        if (request.getMethod().equals("GET") && request.getServletPath().startsWith("/api/products/")) {
        filterChain.doFilter(request, response);
        return;
        }


        // 其他 API 路徑需要驗證 JWT
    String authorHeader = request.getHeader("Authorization");
    String bearer = "Bearer ";
    if (authorHeader != null && authorHeader.startsWith(bearer)) {
        System.out.println("攔截器啟動");
        try {
            String token = authorHeader.substring(bearer.length());
            JwtUtils.getClaims(token); // 驗證 JWT
            System.out.println("成功通過攔截器");
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            System.out.println("登入錯誤" + e.getMessage());
            response.setStatus(HttpStatus.FORBIDDEN.value());
            Map<String, String> err = new HashMap<>();
            err.put("jwt_err", e.getMessage());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000"); // 確保CORS設置
            response.setHeader("Access-Control-Allow-Credentials", "true");
            new ObjectMapper().writeValue(response.getOutputStream(), err);
        }
    } else {
        System.out.println("未取得請求頭");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }
}
}
