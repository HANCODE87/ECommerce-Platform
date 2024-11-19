package com.i4.ecommerce_web;


import com.i4.ecommerce_web.controller.OrderController;
import com.i4.ecommerce_web.pojo.Order;
import com.i4.ecommerce_web.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;



import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void testHandleNewOrder() throws Exception {
        Order mockOrder = new Order();
        mockOrder.setId(1);
        Mockito.when(orderService.handleNewOrder(any(Order.class))).thenReturn(mockOrder);

        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"userId\":123,\"product\":\"Game\",\"quantity\":1}"))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.data.id").value(1));
    }

    @Test
    public void testSearchOrder() throws Exception {
        Order mockOrder = new Order();
        mockOrder.setId(1);
        Mockito.when(orderService.searchOrderById(1)).thenReturn(mockOrder);

        mockMvc.perform(get("/api/orders/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.data.id").value(1));
    }

    @Test
    public void testUpdateOrderSuccess() throws Exception {
        Mockito.when(orderService.updateOrder(any(Order.class))).thenReturn(true);

        mockMvc.perform(put("/api/orders/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\":123,\"product\":\"Updated Game\",\"quantity\":2}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.msg").value("修改訂單成功"));
    }

    @Test
    public void testUpdateOrderNotFound() throws Exception {
        Mockito.when(orderService.updateOrder(any(Order.class))).thenReturn(false);

        mockMvc.perform(put("/api/orders/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\":123,\"product\":\"Updated Game\",\"quantity\":2}"))
               .andExpect(status().isNotFound())
               .andExpect(jsonPath("$.msg").value("修改的訂單不存在"));
    }

    @Test
    public void testGetOrderByUserId() throws Exception {
        Order mockOrder = new Order();
        mockOrder.setId(1);
        mockOrder.setUserId(123);
        Mockito.when(orderService.getOrderByUserId(123)).thenReturn(Collections.singletonList(mockOrder));

        mockMvc.perform(get("/api/orders/user/123"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.data[0].id").value(1))
               .andExpect(jsonPath("$.data[0].userId").value(123));
    }
}
