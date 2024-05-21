package com.michaelakamihe.ecommercebackend.controller;

import com.michaelakamihe.ecommercebackend.model.OrderHistory;
import com.michaelakamihe.ecommercebackend.service.OrderHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderHistoryControllerTest {

    @InjectMocks
    OrderHistoryController orderHistoryController;

    @Mock
    OrderHistoryService orderHistoryService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddOrderToHistory() {
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setOrderId(1L);

        when(orderHistoryService.addOrderToHistory(orderHistory)).thenReturn(orderHistory);

        ResponseEntity<OrderHistory> result = orderHistoryController.addOrderToHistory(orderHistory);

        assertEquals(result.getBody().getOrderId(), orderHistory.getOrderId());
        verify(orderHistoryService, times(1)).addOrderToHistory(orderHistory);
    }

    @Test
    public void testGetAllOrders() {
        OrderHistory orderHistory1 = new OrderHistory();
        OrderHistory orderHistory2 = new OrderHistory();
        List<OrderHistory> orderHistoryList = Arrays.asList(orderHistory1, orderHistory2);

        when(orderHistoryService.getOrderHistory(1L)).thenReturn(orderHistoryList);

        ResponseEntity<List<OrderHistory>> result = orderHistoryController.getAllOrders(1L);

        assertEquals(result.getBody().size(), 2);
        verify(orderHistoryService, times(1)).getOrderHistory(1L);
    }
}
