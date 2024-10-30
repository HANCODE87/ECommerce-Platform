package com.i4.ecommerce_web.mapper;

import com.i4.ecommerce_web.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    /**
     *
     * @param email email
     * @return user
     */
    @Select("select * from user where email = #{email}")
    User findByEmail(String email);

    /**
     * 註冊用戶
     * @param user user
     */
    @Insert("insert into user(username,password,email) values(#{username},#{password},#{email}) ")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    void insert(User user);

    /**
     * 根據username password查詢用戶
     * @param user username,password
     * @return user
     */
    @Select("select * from user where username = #{username} and password = #{password}")
    User getByUsernameAndPassword(User user);

    /**
     * 根據username查詢用戶
     * @return user
     */
    @Select("select * from user where username = #{username}")
    User findByUsername();

    /**
     * 根據id查詢用戶
     * @param id id
     * @return user
     */
    @Select("select * from user where user_id = #{id}")
    User findById(Integer id);

//    @Update("update user set username=#{username},email=#{email} where user_id=#{userId}")
    void updateById(User user);
}
