package com.i4.ecommerce_web.controller;

import com.i4.ecommerce_web.pojo.Result;
import com.i4.ecommerce_web.pojo.User;
import com.i4.ecommerce_web.service.UserService;
import com.i4.ecommerce_web.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     *
     * 註冊會員
     * @param user username,password,email
     * @return Result,userId
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

    /**
     * 會員登入
     * @param user username,password
     * @return Result,jwt
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        log.info("用戶登陸:{}",user);
        User userRespond = userService.login(user);
        //若有返回值則帳號密碼正確，進行登入
        if(userRespond != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", userRespond.getUsername());
            String jwt = JwtUtils.generateJwt(claims);
//            System.out.println(JwtUtils.getClaims(jwt));
            return Result.success("Login successful",jwt);
        }
        return Result.error("Invalid username or password");
    }

    /**
     * 根據id查詢用戶資料
     * @param id id
     * @return Result,userData
     */
    @GetMapping("/{id}")
    public Result getUserInfo(@PathVariable Integer id){
        log.info("取得用戶資料:{}",id);
        User user = userService.getUserInfo(id);
        Map<String,Object> userData = new HashMap<String,Object>();
        userData.put("userId",user.getUserId());
        userData.put("username",user.getUsername());
        userData.put("email",user.getEmail());
        return Result.success(userData);
    }

    @PutMapping("/{id}")
    public Result updateUserInfo(@PathVariable Integer id, @RequestBody User user){
        log.info("更改用戶資料:{}",id);
        user.setUserId(id);
        if(userService.updateUserInfo(user) == null){
            return Result.error("ID錯誤，沒有資料");
        }
        Map<String,Object> userData = new HashMap<String,Object>();
        userData.put("userId",user.getUserId());
        userData.put("username",user.getUsername());
        userData.put("email",user.getEmail());
        return Result.success("更新成功",userData);
    }
}
