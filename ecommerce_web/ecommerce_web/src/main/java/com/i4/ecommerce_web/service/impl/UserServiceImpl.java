package com.i4.ecommerce_web.service.impl;

import com.i4.ecommerce_web.mapper.UserMapper;
import com.i4.ecommerce_web.pojo.User;
import com.i4.ecommerce_web.service.UserService;
import com.i4.ecommerce_web.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 註冊用戶 若信箱已存在則回傳false
     * @param user 用戶的username,password,email
     * @return msg
     */
    @Override
    public Boolean register(User user) {
        //處理username 信箱重複問題
        if(userMapper.findByEmail(user.getEmail()) != null || userMapper.findByUsername(user.getUsername()) != null) {
            return true;
        }
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
        return false;
    }

    /**
     * 根據帳號密碼登入
     * @param user 用戶的username,password
     * @return user
     */
    @Override
    public String login(User user) {
        //如果找不到該筆登入資料則回傳null
        User userRespond = userMapper.getByUsernameAndPassword(user);
        if (userRespond == null){
            return null;
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userRespond.getUserId()); //在payload放入會員id
        claims.put("username", userRespond.getUsername()); //在payload放入會員名稱
        claims.put("role", userRespond.getRole()); //在payload放入身分組
        return JwtUtils.generateJwt(claims);
    }

    /**
     * 根據id取得用戶資料
     * @param id 用戶的id
     * @return user
     */
    @Override
    public Map<String,Object> getUserInfo(Integer id) {
        User user = userMapper.findById(id);
        Map<String,Object> userData = new HashMap<>();
        userData.put("userId",user.getUserId());
        userData.put("username",user.getUsername());
        userData.put("email",user.getEmail());
        return userData;
    }

    /**
     * 根據id更新用戶資料
     * @param user 用戶的id username email
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
     * @param id 用戶的id
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
