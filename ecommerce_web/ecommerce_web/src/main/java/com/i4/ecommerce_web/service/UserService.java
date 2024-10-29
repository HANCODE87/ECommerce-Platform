package com.i4.ecommerce_web.service;

import com.i4.ecommerce_web.pojo.User;

public interface UserService {
    /**
     *
     * @param user username,password,email
     * @return msg
     */
    String register(User user);

    /**
     * 登入用戶
     * @param user username,password
     * @return user object
     */
    User login(User user);
}
