package com.i4.ecommerce_web.service;

import com.i4.ecommerce_web.pojo.User;

import java.util.Map;

public interface UserService {
    /**
     *
     * @param user 用戶的username,password,email
     * @return msg
     */
    Boolean register(User user);

    /**
     * 登入用戶
     * @param user 用戶帳號密碼
     * @return jwt token
     */
    String login(User user);

    /**
     *
     * @param id 用戶id
     * @return userId username email
     */
    Map<String,Object> getUserInfo(Integer id);

    /**
     * 根據id修改用戶資料
     * @param  user id username email
     * @return user
     */
    User updateUserInfo(User user);

    /**
     * 根據id刪除資料
     *
     * @param id id
     * @return Boolean
     */
    Boolean deleteUser(Integer id);
}
