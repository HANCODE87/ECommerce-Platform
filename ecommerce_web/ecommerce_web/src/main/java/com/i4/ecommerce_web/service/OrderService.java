package com.i4.ecommerce_web.service;

import com.i4.ecommerce_web.pojo.Order;

public interface OrderService {
    /**
     * 新增訂單
     * @param order 新增的order
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
}
