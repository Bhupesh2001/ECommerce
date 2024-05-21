package com.michaelakamihe.ecommercebackend.service;

import com.michaelakamihe.ecommercebackend.model.OrderHistoryDetails;
import com.michaelakamihe.ecommercebackend.repo.OrderHistoryDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderHistoryDetailsServiceTest {

    @InjectMocks
    OrderHistoryDetailsService orderHistoryDetailsService;

    @Mock
    OrderHistoryDetailsRepository orderHistoryDetailsRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddOrderHistoryDetails() {
        OrderHistoryDetails orderHistoryDetails1 = new OrderHistoryDetails();
        OrderHistoryDetails orderHistoryDetails2 = new OrderHistoryDetails();
        List<OrderHistoryDetails> orderHistoryDetailsList = Arrays.asList(orderHistoryDetails1, orderHistoryDetails2);

        when(orderHistoryDetailsRepository.saveAll(orderHistoryDetailsList)).thenReturn(orderHistoryDetailsList);

        List<OrderHistoryDetails> result = orderHistoryDetailsService.addOrderHistoryDetails(orderHistoryDetailsList);

        assertEquals(result.size(), 2);
        verify(orderHistoryDetailsRepository, times(1)).saveAll(orderHistoryDetailsList);
    }

    @Test
    public void testGetOrderHistoryDetails() {
        OrderHistoryDetails orderHistoryDetails1 = new OrderHistoryDetails();
        OrderHistoryDetails orderHistoryDetails2 = new OrderHistoryDetails();
        List<OrderHistoryDetails> orderHistoryDetailsList = Arrays.asList(orderHistoryDetails1, orderHistoryDetails2);

        when(orderHistoryDetailsRepository.findByOrderId(1L)).thenReturn(orderHistoryDetailsList);

        List<OrderHistoryDetails> result = orderHistoryDetailsService.getOrderHistoryDetails(1L);

        assertEquals(result.size(), 2);
        verify(orderHistoryDetailsRepository, times(1)).findByOrderId(1L);
    }
}
