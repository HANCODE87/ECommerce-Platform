package com.i4.ecommerce_web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.i4.ecommerce_web.utils.JwtUtils;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JwtFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //若回傳的方法是OPTIONS，則直接放行
        if (request.getMethod().equals("OPTIONS")) {
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
                //驗證並解析jwt token
                String token = authorHeader.substring(bearer.length());
                Claims claims = JwtUtils.getClaims(token);
                //解析payload中的role身分組
                String role = claims.get("role", String.class);
                System.out.println("用戶身分為:" + role);

                //驗證用戶是否具備訪問該路徑的權限
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


    private boolean isAccessAllowed(String role, String requestURI) {
        //設定規則
        Map<String, List<String>> roleAccessMap = new HashMap<>();
        roleAccessMap.put("ADMIN", Arrays.asList("/admin", "/products", "/orders"));
        roleAccessMap.put("USER", Arrays.asList("/products", "/orders"));
        // 根據身分組獲取允許的路徑列表
        List<String> allowedPaths = roleAccessMap.get(role);
        // 檢查是否允許訪問
        return allowedPaths != null && allowedPaths.stream().anyMatch(requestURI::startsWith);
    }
}
