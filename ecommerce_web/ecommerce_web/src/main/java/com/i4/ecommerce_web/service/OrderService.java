package com.i4.ecommerce_web.service;

import com.i4.ecommerce_web.pojo.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    /**
     * 新增訂單並回傳訂單
     * @param order 使用者id, 產品id, 數量
     */
    void addOrder(Order order);

    /**
     * 查詢訂單
     * @param orderId 查詢的id
     * @return 查詢的order
     */
    Order searchOrderById(Integer orderId);

    /**
     * 修改訂單
     * @param order 修改的order
     * @return 修改成功回傳True 失敗回傳False
     */
    Boolean updateOrder(Order order);

    /**
     * 根據userId查詢訂單
     * @param userId 使用者的id
     * @return userId 相同的 order
     */
    List<Order> getOrderByUserId(Integer userId);

    /**
     * 尋找會員是否有相同productId，並且狀態為未完成
     * @param order 要比對的訂單
     * @return 第一個符合條件的訂單
     */
    Order handleNewOrder(Order order);

    /**
     * 尋找userId相同的訂單
     * @param userId 使用者id
     * @return userId相同的訂單List
     */
    List<Order> searchOrderByUserId(Integer userId);
}
