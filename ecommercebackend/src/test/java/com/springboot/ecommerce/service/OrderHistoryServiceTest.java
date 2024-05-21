package com.michaelakamihe.ecommercebackend.service;

import com.michaelakamihe.ecommercebackend.model.OrderHistory;
import com.michaelakamihe.ecommercebackend.repo.OrderHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderHistoryServiceTest {

    @InjectMocks
    OrderHistoryService orderHistoryService;

    @Mock
    OrderHistoryRepository orderHistoryRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddOrderToHistory() {
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setOrderId(1L);

        when(orderHistoryRepository.save(orderHistory)).thenReturn(orderHistory);

        OrderHistory result = orderHistoryService.addOrderToHistory(orderHistory);

        assertEquals(result.getOrderId(), orderHistory.getOrderId());
        verify(orderHistoryRepository, times(1)).save(orderHistory);
    }

    @Test
    public void testGetOrderHistory() {
        OrderHistory orderHistory1 = new OrderHistory();
        OrderHistory orderHistory2 = new OrderHistory();
        List<OrderHistory> orderHistoryList = Arrays.asList(orderHistory1, orderHistory2);

        when(orderHistoryRepository.findByUserId(1L)).thenReturn(orderHistoryList);

        List<OrderHistory> result = orderHistoryService.getOrderHistory(1L);

        assertEquals(result.size(), 2);
        verify(orderHistoryRepository, times(1)).findByUserId(1L);
    }
}
