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
    //     只會攔截一次
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //不是登入就攔截
        System.out.println("攔截器啟動");
        //如果不是進行登入或註冊就進行驗證
        if(!request.getServletPath().equals("/api/user/login") && !request.getServletPath().equals("/api/user/register")){
            System.out.println("捕捉到了!");
            String authorHeader = request.getHeader("Authorization");
            String bearer = "Bearer ";
            if (authorHeader != null && authorHeader.startsWith(bearer)){
                try{
                    String token = authorHeader.substring(bearer.length());
                    JwtUtils.getClaims(token); //可以得到payload就是可以解析token
                    filterChain.doFilter(request, response);
                }catch (Exception e){
                    System.out.println("Error : " + e); //調適用:輸出Exception
                    response.setStatus(HttpStatus.FORBIDDEN.value());//狀態碼403
                    //回傳json格式的錯誤
                    Map<String, String> err = new HashMap<>();
                    err.put("jwt_err", e.getMessage());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), err);
                }
            }
            else {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            }
        }
        else{
            filterChain.doFilter(request, response);
        }
    }


}
