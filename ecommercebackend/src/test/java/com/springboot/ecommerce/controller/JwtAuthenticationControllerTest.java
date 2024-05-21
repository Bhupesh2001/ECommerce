//package com.michaelakamihe.ecommercebackend.controller;
//
//import com.michaelakamihe.ecommercebackend.config.JwtUtil;
//import com.michaelakamihe.ecommercebackend.model.User;
//import com.michaelakamihe.ecommercebackend.repo.UserRepository;
//import com.michaelakamihe.ecommercebackend.service.JwtUserDetailsService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class JwtAuthenticationControllerTest {
//
//    @InjectMocks
//    JwtAuthenticationController jwtAuthenticationController;
//
//    @Mock
//    UserRepository userRepository;
//
//    @Mock
//    JwtUserDetailsService jwtUserDetailsService;
//
//    @Mock
//    JwtUtil jwtUtil;
//
//    @Mock
//    AuthenticationManager authenticationManager;
//
//    @BeforeEach
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testGetCurrentUser() {
//        User user = new User();
//        user.setUsername("testUser");
//
//        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
//
//        when(jwtUserDetailsService.loadUserByUsername(user.getUsername())).thenReturn(userDetails);
//        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
//
//        ResponseEntity<User> result = jwtAuthenticationController.getCurrentUser(null);
//
//        assertEquals(result.getBody().getUsername(), user.getUsername());
//        verify(userRepository, times(1)).findByUsername(user.getUsername());
//    }
//
//    @Test
//    public void testRegisterUser() {
//        User user = new User();
//        user.setUsername("testUser");
//
//        Map<String, Object> userMap = new HashMap<>();
//        userMap.put("username", "testUser");
//
//        when(jwtUserDetailsService.save(user)).thenReturn(user);
//        when(jwtUtil.generateToken(any())).thenReturn("token");
//
//        ResponseEntity<?> result = jwtAuthenticationController.registerUser(userMap);
//
//        assertEquals(((Map) result.getBody()).get("token"), "token");
//        verify(jwtUserDetailsService, times(1)).save(user);
//    }
//
//    @Test
//    public void testAuthenticateUser() {
//        Map<String, String> userMap = new HashMap<>();
//        userMap.put("username", "testUser");
//        userMap.put("password", "testPassword");
//
//        when(jwtUtil.generateToken(any())).thenReturn("token");
//
//        ResponseEntity<?> result = jwtAuthenticationController.authenticateUser(userMap);
//
//        assertEquals(((Map) result.getBody()).get("token"), "token");
//        verify(authenticationManager, times(1)).authenticate(any());
//    }
//}
