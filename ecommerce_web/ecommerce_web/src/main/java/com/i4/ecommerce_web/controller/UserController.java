package com.i4.ecommerce_web.controller;

import com.i4.ecommerce_web.pojo.Result;
import com.i4.ecommerce_web.pojo.User;
import com.i4.ecommerce_web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     *
     * 註冊會員
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        log.info("註冊會員:{}",user);
        String msg = userService.register(user);
        if(msg.equals("User already exists")){
            return Result.error(msg);
        }
        return Result.success(msg,user.getUserId());
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        log.info("用戶登陸:{}",user);
        return Result.success();
    }

}
