package com.i4.ecommerce_web.controller;

import com.i4.ecommerce_web.pojo.Order;
import com.i4.ecommerce_web.pojo.Result;
import com.i4.ecommerce_web.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 新增訂單
     * @param order 新增的order
     * @return message
     */
    @PostMapping()
    public ResponseEntity<Result> addOrder(@RequestBody Order order) {
        log.info("新增訂單:{}",order);
        orderService.addOrder(order);
        return new ResponseEntity<>(Result.success("新增訂單成功"), HttpStatus.CREATED);
    }

    /**
     * 根據id查詢訂單
     * @param orderId 查詢id
     * @return order
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<Result> searchOrder(@PathVariable Integer orderId) {
        log.info("查詢訂單:{}",orderId);
        return new ResponseEntity<>(Result.success(orderService.searchOrder(orderId)), HttpStatus.OK);
    }

    /**
     * 根據id修改訂單
     * @param orderId 修改id
     * @param order 更改過的Order
     * @return message
     */
    @PutMapping("/{orderId}")
    public ResponseEntity<Result> updateOrder(@PathVariable Integer orderId, @RequestBody Order order) {
        log.info("修改訂單:{}",orderId);
        order.setId(orderId);
        if (orderService.updateOrder(order)){
            return new ResponseEntity<>(Result.success("修改訂單成功"),HttpStatus.OK);
        }
        return new ResponseEntity<>(Result.error("修改的訂單不存在"),HttpStatus.NOT_FOUND);
    }
}
