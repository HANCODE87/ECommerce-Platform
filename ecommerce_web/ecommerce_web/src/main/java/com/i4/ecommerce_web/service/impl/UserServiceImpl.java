package com.i4.ecommerce_web.service.impl;

import com.i4.ecommerce_web.mapper.UserMapper;
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
     * @param user username,password,email
     * @return msg
     */
    @Override
    public String register(User user) {
        //處理username 信箱重複問題
        if(userMapper.findByEmail(user.getEmail()) != null || userMapper.findByUsername()!=null) {
            return "User already exists";
        }
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
        return "User registered successfully";
    }

    /**
     *
     * @param user username,password
     * @return user
     */
    @Override
    public User login(User user) {
        return userMapper.getByUsernameAndPassword(user);
    }


}
