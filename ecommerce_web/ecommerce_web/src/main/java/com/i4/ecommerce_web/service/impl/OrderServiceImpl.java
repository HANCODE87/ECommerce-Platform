package com.i4.ecommerce_web.service.impl;

import com.i4.ecommerce_web.mapper.OrderMapper;
import com.i4.ecommerce_web.pojo.Order;
import com.i4.ecommerce_web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
    public Order searchOrder(Integer orderId) {
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
}
