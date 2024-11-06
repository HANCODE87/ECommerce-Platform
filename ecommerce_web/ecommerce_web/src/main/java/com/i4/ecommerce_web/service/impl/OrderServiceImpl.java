package com.i4.ecommerce_web.service.impl;

import com.i4.ecommerce_web.mapper.OrderMapper;
import com.i4.ecommerce_web.pojo.Order;
import com.i4.ecommerce_web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    /**
     * 新增order
     * @param order 新增的order
     */
    @Override
    public void addOrder(Order order) {
        order.setCreateTime(LocalDateTime.now());
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
}
