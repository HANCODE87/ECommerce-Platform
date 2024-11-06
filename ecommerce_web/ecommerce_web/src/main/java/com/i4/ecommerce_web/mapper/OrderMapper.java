package com.i4.ecommerce_web.mapper;

import com.i4.ecommerce_web.pojo.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrderMapper {
    /**
     * 新增order
     * @param order 新增的order
     */
    @Insert("insert into orders(user_id, product_id, quantity, status) " +
            "values(#{userId}, #{productId}, #{quantity}, #{status})")
    void addOrder(Order order);

    /**
     * 根據id查詢order
     * @param orderId order的id
     * @return 查詢的order
     */
    @Select("select * from orders where id = #{orderId}")
    Order findById(Integer orderId);

    void updateById(Order order);
}

