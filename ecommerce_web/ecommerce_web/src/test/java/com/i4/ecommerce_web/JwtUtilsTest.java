package com.i4.ecommerce_web;

import com.i4.ecommerce_web.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class JwtUtilsTest {

    @Autowired
    private JwtUtils jwtUtils;

    @Test
    void test(){
        String username = "testUser";
        String token = jwtUtils.generateToken(username);

        assertNotNull(token);  // 確認生成的 token 不是 null
        assertTrue(token.contains("."));  // 確認 token 具備 JWT 標準格式
    }

}
