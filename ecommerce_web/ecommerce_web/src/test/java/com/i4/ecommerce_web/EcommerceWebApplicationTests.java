package com.i4.ecommerce_web;
import com.i4.ecommerce_web.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EcommerceWebApplicationTests {


    @Test
    void testGenJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", 5);
        claims.put("name", "tm");
        SecretKey key = Jwts.SIG.HS256.key().build();

        String jwt = Jwts.builder()
                .signWith(key) // 使用密鑰，這裡不需指定算法
                .setClaims(claims)// 添加自定義信息
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 設置有效期限
                .compact();//生成字符串


        System.out.println(jwt);

        Claims parseClaims = Jwts.parser().verifyWith(key).setSigningKey(key) // 使用相同的密鑰來解碼
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        System.out.println("Payload: " + parseClaims + parseClaims.getSubject()); // 獲取整個 payload（claims）

    }


    @Test
    void test(){
        String username = "testUser";
        HashMap claims = new HashMap();
        claims.put("username", username);
        String token = JwtUtils.generateJwt(claims);

        assertNotNull(token);  // 確認生成的 token 不是 null
        assertTrue(token.contains("."));  // 確認 token 具備 JWT 標準格式
        System.out.println(JwtUtils.getClaims(token));

    }

}
