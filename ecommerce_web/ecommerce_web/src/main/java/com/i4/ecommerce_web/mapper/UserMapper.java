package com.i4.ecommerce_web.mapper;

import com.i4.ecommerce_web.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

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
     *
     * @param user username,password
     * @return user
     */
    @Select("select * from user where username = #{username} and password = #{password}")
    User getByUsernameAndPassword(User user);

    /**
     * @param user username
     * @return user
     */
    @Select("select * from user where username = #{username}")
    User findByUsername(User user);
}
