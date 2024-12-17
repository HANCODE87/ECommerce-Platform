package com.i4.ecommerce_web.controller;

import com.i4.ecommerce_web.pojo.Result;
import com.i4.ecommerce_web.pojo.User;
import com.i4.ecommerce_web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    /**
     *
     * 註冊會員
     * @param user 會員的username,password,email
     * @return 成功或失敗的String
     */
    @PostMapping("/register")
    public ResponseEntity<Result<Void>> register(@RequestBody User user){
        log.info("註冊會員:{}",user);
        Boolean userExists = userService.register(user);
        if(userExists){
            return new ResponseEntity<>(Result.error("此帳號或信箱已註冊過"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(Result.success("註冊成功"), HttpStatus.CREATED);
    }

    /**
     * 會員登入
     * @param user 會員的username,password
     * @return 登入成功返回成功訊息會得到jwt 失敗則返回錯誤訊息並且jwt為空
     */
    @PostMapping("/login")
    public ResponseEntity<Result<String>> login(@RequestBody User user){
        log.info("登錄請求：用戶名={} 密碼={}", user.getUsername(), user.getPassword());
        String token = userService.login(user);
        //若有返回值則帳號密碼正確，進行登入
        if(token != null){
            return new ResponseEntity<>(Result.success("成功登入",token), HttpStatus.OK);
        }
        return new ResponseEntity<>(Result.error("帳號或密碼錯誤", null), HttpStatus.UNAUTHORIZED);
    }

    /**
     * 根據id查詢用戶資料
     * @param id 會員id
     * @return 會員資料
     */
    @GetMapping("/{id}")
    public ResponseEntity<Result<Map<String,Object>>> getUserInfo(@PathVariable Integer id){
        log.info("取得會員資料:{}",id);
        return new ResponseEntity<>(Result.success(userService.getUserInfo(id)), HttpStatus.FOUND);
    }

    /**
     * 根據id更新資料
     * @param id 產品id
     * @param user 會員資料
     * @return 成功或失敗的訊息
     */
    @PutMapping("/{id}")
    public ResponseEntity<Result<Void>> updateUserInfo(@PathVariable Integer id, @RequestBody User user){
        log.info("更改用戶資料:{}",id);
        user.setUserId(id);
        if(userService.updateUserInfo(user) == null){
            return new ResponseEntity<>(Result.error("ID錯誤，沒有資料"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Result.success(),HttpStatus.OK);
    }

    /**
     * 刪除用戶
     * @param id 用戶id
     * @return 成功或失敗的訊息
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Result<Void>> deleteUser(@PathVariable Integer id){
        log.info("刪除用戶:{}",id);
        if (userService.deleteUser(id)){
            return new ResponseEntity<>(Result.success(),HttpStatus.OK);
        }
        return new ResponseEntity<>(Result.error("刪除失敗"), HttpStatus.NOT_FOUND);
    }
}
