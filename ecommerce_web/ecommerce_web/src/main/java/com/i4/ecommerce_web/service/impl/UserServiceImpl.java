package com.i4.ecommerce_web.service.impl;

import com.i4.ecommerce_web.mapper.UserMapper;
import com.i4.ecommerce_web.pojo.Result;
import com.i4.ecommerce_web.pojo.User;
import com.i4.ecommerce_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 註冊用戶 若信箱已存在則回傳false
     * @param user
     */
    @Override
    public String register(User user) {
        //處理信箱重複問題
        if(userMapper.findByEmail(user.getEmail()) != null) {
            return "User already exists";
        }
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
        return "User registered successfully";
    }

    @Override
    public String login(User user) {

        return "";
    }


}
