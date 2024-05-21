package com.michaelakamihe.ecommercebackend.controller;

import com.michaelakamihe.ecommercebackend.model.OrderHistoryDetails;
import com.michaelakamihe.ecommercebackend.service.OrderHistoryDetailsService;
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

public class OrderHistoryDetailsControllerTest {

    @InjectMocks
    OrderHistoryDetailsController orderHistoryDetailsController;

    @Mock
    OrderHistoryDetailsService orderHistoryDetailsService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetOrderHistoryDetails() {
        OrderHistoryDetails orderHistoryDetails1 = new OrderHistoryDetails();
        OrderHistoryDetails orderHistoryDetails2 = new OrderHistoryDetails();
        List<OrderHistoryDetails> orderHistoryDetailsList = Arrays.asList(orderHistoryDetails1, orderHistoryDetails2);

        when(orderHistoryDetailsService.getOrderHistoryDetails(1L)).thenReturn(orderHistoryDetailsList);

        ResponseEntity<List<OrderHistoryDetails>> result = orderHistoryDetailsController.getOrderHistoryDetails(1L);

        assertEquals(result.getBody().size(), 2);
        verify(orderHistoryDetailsService, times(1)).getOrderHistoryDetails(1L);
    }

    @Test
    public void testAddOrderHistoryDetails() {
        OrderHistoryDetails orderHistoryDetails1 = new OrderHistoryDetails();
        OrderHistoryDetails orderHistoryDetails2 = new OrderHistoryDetails();
        List<OrderHistoryDetails> orderHistoryDetailsList = Arrays.asList(orderHistoryDetails1, orderHistoryDetails2);

        when(orderHistoryDetailsService.addOrderHistoryDetails(orderHistoryDetailsList)).thenReturn(orderHistoryDetailsList);

        ResponseEntity<List<OrderHistoryDetails>> result = orderHistoryDetailsController.addOrderHistoryDetails(orderHistoryDetailsList);

        assertEquals(result.getBody().size(), 2);
        verify(orderHistoryDetailsService, times(1)).addOrderHistoryDetails(orderHistoryDetailsList);
    }
}
