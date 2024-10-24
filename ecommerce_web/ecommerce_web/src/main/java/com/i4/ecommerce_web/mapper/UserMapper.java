package com.i4.ecommerce_web.mapper;

import com.i4.ecommerce_web.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    /**
     * 根據email查詢用戶
     */
    @Select("select user_id,username,password,email,create_time,update_time from user where email = #{email}")
    User findByEmail(String email);

    /**
     * 註冊用戶
     * @param user
     */
    @Insert("insert into user(username,password,email) values(#{username},#{password},#{email}) ")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    void insert(User user);
}
