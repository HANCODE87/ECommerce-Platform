package com.i4.ecommerce_web.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer userId;
    private String username;
    private String password;
    private String email;
    private String role;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
