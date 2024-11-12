package com.i4.ecommerce_web.service;

import com.i4.ecommerce_web.pojo.Order;

import java.util.List;

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
    Order searchOrder(Integer orderId);

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
}
