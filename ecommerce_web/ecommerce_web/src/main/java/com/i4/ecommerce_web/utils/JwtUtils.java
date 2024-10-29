package com.i4.ecommerce_web.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static final String SECRET = "ALONGSERETKEYNEEDSVERYLONGVERYLONGVERYLONGVERYLONGVERYLONG"; // 這裡可以從 application.yml 加載
    private static final long EXPIRATION_TIME = 86400000L; // 1 天

    /**
     * 生成JWT claims
     * @param claims
     * @return
     */
    public static String generateJwt(Map<String,Object> claims) {
        return Jwts.builder() //調用builder靜態方法 創建JWT生成器 用來設置token內容 生成JWT字串
                .setClaims(claims)//設定subject
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) //設定過期時間
                .signWith(SignatureAlgorithm.HS256, SECRET) //設定算法以及密鑰
                .compact(); //壓縮以後生成JWT字串
    }

    /**
     * 解析JWT Token
     * @param token
     * @return
     */
    public static Claims getClaims(String token){

        return Jwts.parser() //調用parser靜態方法
                .setSigningKey(SECRET) //設定密鑰解析JWT的簽名
                .build() //構建解析器對象開始解析
                .parseSignedClaims(token) //將token解析成claims對象 也會驗證JWT簽名是否正確 不正確會拋出異常
                .getBody(); //取得payload
    }

    /**
     * 確定token是否過期
     * @param token
     * @return
     */
    public static boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }

}
