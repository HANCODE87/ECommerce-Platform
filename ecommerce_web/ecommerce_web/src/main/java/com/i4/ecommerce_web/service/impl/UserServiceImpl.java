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
    public Boolean register(User user) {
        //處理username 信箱重複問題
        if(userMapper.findByEmail(user.getEmail()) != null || userMapper.findByUsername()!=null) {
            return true;
        }
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
        return false;
    }

    /**
     * 根據帳號密碼登入
     * @param user username,password
     * @return user
     */
    @Override
    public User login(User user) {
        return userMapper.getByUsernameAndPassword(user);
    }

    /**
     * 根據id取得用戶資料
     * @param id id
     * @return user
     */
    @Override
    public User getUserInfo(Integer id) {
        return userMapper.findById(id);
    }

    /**
     * 根據id更新用戶資料
     * @param user id username email
     * @return user
     */
    @Override
    public User updateUserInfo(User user) {
        if (userMapper.findById(user.getUserId()) == null){
            System.out.println("查無資料");
            return null;
        }
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);
        return userMapper.findById(user.getUserId());
    }

    /**
     * 根據id刪除資料
     * @param id id
     * @return Boolean
     */
    @Override
    public Boolean deleteUser(Integer id) {
        if(userMapper.findById(id) == null){
            return false;
        }
        userMapper.deleteById(id);
        return true;
    }


}
