package com.i4.ecommerce_web.mapper;

import com.i4.ecommerce_web.pojo.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface OrderMapper {
    /**
     * 新增order
     * @param order 新增的order
     */

    void addOrder(Order order);

    /**
     * 根據訂單id查詢該order
     * @param orderId 要查詢order的id
     * @return 查詢到的order
     */
    @Select("select * from orders where id = #{orderId}")
    Order findById(Integer orderId);

    /**
     * 更新訂單
     * @param order 要更新的訂單
     */
    void updateById(Order order);

    /**
     * 根據userId查詢order
     * @param userId 使用者的id
     * @return userId相同的order list
     */
    @Select("select * from orders where user_id = #{userId}")
    List<Order> findByUserId(Integer userId);

    /**
     * 尋找同一用戶與新訂單相同商品且狀態為未完成的訂單
     * @param userId 要刪除的使用者id
     * @param productId 要刪除的產品id
     * @return 查詢到的訂單
     */
    @Select("select * from orders where user_id = #{userId} and product_id = #{productId} and status = '未完成'")
    Order findByUserIdAndProductIdAndIncomplete(Integer userId, Integer productId);

    /**
     * 根據orderId刪除訂單
     * @param orderId 需要刪除的orderId
     */
    @Delete("delete from orders where id = #{orderId}")
    void deleteById(Integer orderId);

    /**
     * 根據使用者id和產品id刪除訂單
     * @param userId 要刪除的使用者id
     * @param productId 要刪除的產品id
     */
    @Delete("delete from orders where user_id = #{userId} and product_id = #{productId} and status = '未完成'")
    void deleteByUserIdAndProductId(Integer userId, Integer productId);
}

