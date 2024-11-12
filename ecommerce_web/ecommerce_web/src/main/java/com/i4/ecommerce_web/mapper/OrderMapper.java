package com.i4.ecommerce_web.mapper;

import com.i4.ecommerce_web.pojo.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderMapper {
    /**
     * 新增order
     * @param order 新增的order
     */

    void addOrder(Order order);

    /**
     * 根據id查詢order
     * @param orderId order的id
     * @return 查詢的order
     */
    @Select("select * from orders where id = #{orderId}")
    Order findById(Integer orderId);

    void updateById(Order order);

    /**
     * 根據userId查詢order
     * @param userId 使用者的id
     * @return userId相同的order
     */
    @Select("select * from orders where user_id = #{userId}")
    List<Order> findByUserId(Integer userId);
}

