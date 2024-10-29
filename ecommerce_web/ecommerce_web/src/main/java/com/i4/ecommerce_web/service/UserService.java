package com.i4.ecommerce_web.service;

import com.i4.ecommerce_web.pojo.User;

public interface UserService {
    /**
     * 註冊用戶
     * @param user
     */
    String register(User user);

    /**
     * 登入用戶
     * @param user
     * @return
     */
    User login(User user);
}
