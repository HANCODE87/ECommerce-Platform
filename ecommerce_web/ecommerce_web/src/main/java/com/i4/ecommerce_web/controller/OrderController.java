package com.i4.ecommerce_web.controller;

import com.i4.ecommerce_web.pojo.Order;
import com.i4.ecommerce_web.pojo.Result;
import com.i4.ecommerce_web.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 新增訂單
     * @param order 使用者id,產品id,數量
     * @return 建立的訂單
     */
    @PostMapping()
    public ResponseEntity<Result<Order>> handleNewOrder(@RequestBody Order order) {
        log.info("新增訂單:{}", order);
        return new ResponseEntity<>(Result.success(orderService.handleNewOrder(order)), HttpStatus.CREATED);
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

    /**
     * 根據orderId刪除訂單
     * @param orderId 需要刪除的orderId
     * @return 成功刪除訊息或者錯誤訊息
     */
    @DeleteMapping()
    public ResponseEntity<Result<Void>> deleteOrder(@RequestParam Integer orderId) {
        log.info("刪除訂單:{}",orderId);
        return new ResponseEntity<>(Result.error(orderService.deleteOrder(orderId)),HttpStatus.NOT_FOUND);
    }

    /**
     * 根據使用者id和產品id刪除訂單
     * @param userId 要刪除的使用者id
     * @param productId 要刪除的產品id
     * @return 成功刪除訊息或者錯誤訊息
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Result<Void>> deleteOrderByUserIdAndProductId(@RequestParam Integer userId,@RequestParam Integer productId) {
        log.info("刪除訂單:{},{}",userId,productId);
        if (orderService.deleteOrderByUserIdAndProductId(userId, productId)){
            return new ResponseEntity<>(Result.success(),HttpStatus.OK);
        }
        return new ResponseEntity<>(Result.error("刪除失敗:找不到該筆訂單"),HttpStatus.NOT_FOUND);
    }
}
