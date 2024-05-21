package com.michaelakamihe.ecommercebackend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.michaelakamihe.ecommercebackend.config.JwtUtil;
import com.michaelakamihe.ecommercebackend.model.User;
import com.michaelakamihe.ecommercebackend.service.JwtUserDetailsService;
import com.michaelakamihe.ecommercebackend.service.UserService;

public class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Mock
    JwtUserDetailsService jwtUserDetailsService;

    @Mock
    JwtUtil jwtUtil;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUsers() {
        User user1 = new User();
        User user2 = new User();
        List<User> userList = Arrays.asList(user1, user2);

        when(userService.getUsers()).thenReturn(userList);

        ResponseEntity<List<User>> result = userController.getUsers();

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody().size(), 2);
        verify(userService, times(1)).getUsers();
    }

    @Test
    public void testGetUser() {
        User user = new User();
        user.setId(1L);

        when(userService.getUser(1L)).thenReturn(user);

        ResponseEntity<User> result = userController.getUser(1L);

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody().getId(), user.getId());
        verify(userService, times(1)).getUser(1L);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("username", "testUser");

        when(userService.updateUser(1L, user)).thenReturn(user);

        ResponseEntity<User> result = userController.updateUser(1L, userMap);

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody().getUsername(), "testUser");
        verify(userService, times(1)).updateUser(1L, user);
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userService).deleteUser(1L);

        ResponseEntity<?> result = userController.deleteUser(1L);

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        verify(userService, times(1)).deleteUser(1L);
    }
}
