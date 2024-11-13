package com.i4.ecommerce_web.service.impl;

import com.i4.ecommerce_web.mapper.OrderMapper;
import com.i4.ecommerce_web.pojo.Order;
import com.i4.ecommerce_web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    /**
     * 新增order 並回傳order
     * @param order 新增的order
     */
    @Override
    public void addOrder(Order order) {
        order.setCreateTime(LocalDateTime.now());
        order.setStatus("未完成");//預設未完成
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
        //需要修改的訂單一定是未完成的
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
     * 尋找會員是否有相同orderId，並且狀態為未完成
     * @param order 要比對的訂單
     * @return 第一個符合條件的訂單
     */
    @Override
    public Optional<Integer> findMatchOrderId(Order order) {

        return orderMapper.findByUserId(order.getUserId()).stream().filter(o -> o.getProductId().equals(order.getProductId()) && o.getStatus().equals("未完成"))
                .map(Order::getId).findFirst();
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


}
