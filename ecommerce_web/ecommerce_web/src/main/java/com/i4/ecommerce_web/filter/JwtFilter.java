package com.i4.ecommerce_web.filter;

import com.i4.ecommerce_web.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    // 只會攔截一次
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //不是登入就攔截
        if(!request.getServletPath().equals("api/user/login") || !request.getServletPath().equals("api/user/register")){
            String authorHeader = request.getHeader("Authorization");
            String bearer = "Bearer ";
            if (authorHeader != null && authorHeader.startsWith(bearer)){
                    String token = authorHeader.substring(bearer.length());
                    Claims claims = JwtUtils.getClaims(token);
            }
        }
    }
}
