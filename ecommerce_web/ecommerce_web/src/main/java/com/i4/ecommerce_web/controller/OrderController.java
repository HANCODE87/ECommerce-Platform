package com.i4.ecommerce_web.controller;

import com.i4.ecommerce_web.pojo.Order;
import com.i4.ecommerce_web.pojo.Result;
import com.i4.ecommerce_web.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 新增訂單
     * @param order 使用者id,產品id,數量
     * @return 成功訊息
     */
    @PostMapping()
    public ResponseEntity<Result<Void>> addOrder(@RequestBody Order order) {
        log.info("新增訂單:{}", order);
        //查詢與userId相同的Order

        // 使用 ifPresentOrElse 來簡化判斷邏輯
        return orderService.findMatchOrderId(order).map(existOrderId -> {
            //如果有productId重複的訂單，則查詢該訂單並修改訂單
            Order existOrder = orderService.searchOrderById(existOrderId);
            //修改數量
            existOrder.setQuantity(existOrder.getQuantity() + order.getQuantity());
            orderService.updateOrder(existOrder);
            return new ResponseEntity<>(Result.success("新增訂單成功"), HttpStatus.CREATED);
        }).orElseGet(() -> {
            //如果沒有productId重複的訂單，則新增訂單
                orderService.addOrder(order);
                return new ResponseEntity<>(Result.success("新增訂單成功"), HttpStatus.CREATED);
        });
    }

    /**
     * 根據訂單id查詢訂單
     * @param orderId 訂單id
     * @return 訂單
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<Result<Order>> searchOrder(@PathVariable Integer orderId) {
        log.info("查詢訂單:{}",orderId);
        return new ResponseEntity<>(Result.success(orderService.searchOrderById(orderId)), HttpStatus.OK);
    }

    /**
     * 根據訂單id修改訂單
     * @param orderId 要修改的訂單id
     * @param order 更改過的訂單
     * @return 成功或者錯誤訊息
     */
    @PutMapping("/{orderId}")
    public ResponseEntity<Result<Void>> updateOrder(@PathVariable Integer orderId, @RequestBody Order order) {
        log.info("修改訂單:{}",orderId);
        order.setId(orderId);
        if (orderService.updateOrder(order)){
            return new ResponseEntity<>(Result.success("修改訂單成功"),HttpStatus.OK);
        }
        return new ResponseEntity<>(Result.error("修改的訂單不存在"),HttpStatus.NOT_FOUND);
    }

    /**
     * 根據使用者id查詢訂單
     * @param userId 使用者id
     * @return 使用者id相同的order
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<Result<List<Order>>> getOrderByUserId(@PathVariable Integer userId) {
        log.info("查詢用戶:{}的訂單",userId);
        return new ResponseEntity<>(Result.success(orderService.getOrderByUserId(userId)), HttpStatus.OK);
    }
}
