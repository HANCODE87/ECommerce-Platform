package com.i4.ecommerce_web.service.impl;

import com.i4.ecommerce_web.mapper.OrderMapper;
import com.i4.ecommerce_web.pojo.Order;
import com.i4.ecommerce_web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    /**
     * 確認是否有未完成且userId與productId相同的訂單，有則修改數量，否則新增訂單
     * @param order 新增的order
     */
    @Override
    public void addOrder(Order order) {
        order.setCreateTime(LocalDateTime.now());
        order.setStatus("未完成");
        orderMapper.addOrder(order);
    }

    /**
     * 根據id查詢order
     * @param orderId 查詢的id
     * @return 找到的order
     */
    @Override
    public Order searchOrderById(Integer orderId) {
        return orderMapper.findById(orderId);
    }

    /**
     * 根據id修改order
     * @param order 修改的order
     * @return 是否修改成功
     */
    @Override
    public Boolean updateOrder(Order order) {
        if(orderMapper.findById(order.getId()) == null){
            return false;
        }
        order.setCreateTime(LocalDateTime.now());
        order.setStatus("未完成");
        orderMapper.updateById(order);
        return true;
    }

    /**
     * 根據userId查詢order
     * @param userId 使用者的id
     * @return userId 相同的 order
     */
    @Override
    public List<Order> getOrderByUserId(Integer userId) {
        return orderMapper.findByUserId(userId);
    }

    /**
     * 尋找會員是否有相同productId，並且狀態為未完成
     * @param order 要比對的訂單
     * @return 建立的訂單
     */
    @Transactional
    public Order handleNewOrder(Order order) {
        Order existOrder = findSameIncompleteOrder(order);

        if (existOrder == null) {
            //如果沒有符合的order，則新增該order
            addOrder(order);
            //回傳新增後的order
            return findSameIncompleteOrder(order);
        }
        //如果有符合的order，則修改數量
        existOrder.setQuantity(order.getQuantity() + existOrder.getQuantity());
        updateOrder(existOrder);

        return existOrder;
    }


    /**
     * 查詢userId,productId,status都相同的訂單
     * @param order 新的order
     * @return 找到的符合條件的order
     */
    public Order findSameIncompleteOrder(Order order) {
        return orderMapper.findByUserIdAndProductIdAndIncomplete(order);
    }

    /**
     * 尋找userId相同的訂單
     * @param userId 使用者id
     * @return userId相同的訂單List
     */
    @Override
    public List<Order> searchOrderByUserId(Integer userId) {
        return orderMapper.findByUserId(userId);
    }

    /**
     * 根據orderId刪除訂單
     * @param orderId 需要刪除的orderId
     * @return 成功刪除訊息或者錯誤訊息
     */
    @Override
    public String deleteOrder(Integer orderId) {
        orderMapper.deleteById(orderId);
        return "訂單刪除成功!";
    }

    /**
     * 根據使用者id和產品id刪除訂單
     * @param userId 要刪除的使用者id
     * @param productId 要刪除的產品id
     * @return 成功刪除訊息或者錯誤訊息
     */
    @Override
    public String deleteOrderByUserIdAndProductId(Integer userId, Integer productId) {
        orderMapper.deleteByuserIdAndProductId(userId, productId);
        return "訂單刪除成功!";
    }


}
