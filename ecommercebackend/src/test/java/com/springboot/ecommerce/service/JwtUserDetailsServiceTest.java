package com.michaelakamihe.ecommercebackend.service;

import com.michaelakamihe.ecommercebackend.model.User;
import com.michaelakamihe.ecommercebackend.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class JwtUserDetailsServiceTest {

    @InjectMocks
    JwtUserDetailsService jwtUserDetailsService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder bcryptEncoder;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoadUserByUsername() {
        User user = new User();
        user.setUsername("testUser");

        when(userRepository.findByUsername("testUser")).thenReturn(user);

        UserDetails result = jwtUserDetailsService.loadUserByUsername("testUser");

        assertEquals(result.getUsername(), user.getUsername());
        verify(userRepository, times(1)).findByUsername("testUser");
    }

    @Test
    public void testLoadUserByUsernameNotFound() {
        when(userRepository.findByUsername("testUser")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            jwtUserDetailsService.loadUserByUsername("testUser");
        });
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("testUser");

        when(bcryptEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);

        User result = jwtUserDetailsService.save(user);

        assertEquals(result.getUsername(), user.getUsername());
        verify(userRepository, times(1)).save(user);
    }
}
