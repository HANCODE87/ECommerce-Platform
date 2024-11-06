package com.i4.ecommerce_web.service;

import com.i4.ecommerce_web.pojo.User;

public interface UserService {
    /**
     *
     * @param user username,password,email
     * @return msg
     */
    Boolean register(User user);

    /**
     * 登入用戶
     * @param user username,password
     * @return user object
     */
    User login(User user);

    /**
     *
     * @param id id
     * @return userId username email
     */
    User getUserInfo(Integer id);

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
