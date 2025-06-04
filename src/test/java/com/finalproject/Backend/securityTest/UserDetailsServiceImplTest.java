package com.finalproject.Backend.securityTest;

import com.finalproject.Backend.model.User;
import com.finalproject.Backend.repository.UserRepository;
import com.finalproject.Backend.security.UserDetailsServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setName("Test User");
        testUser.setEmail("test@example.com");
        testUser.setPassword("encodedPassword123");
    }

    @Test
    @DisplayName("Test 1: loadUserByUsername - Should return UserDetails for existing email")
    void loadUserByUsername_ExistingUser_ReturnsUserDetails() {
        // ARRANGE
        String existingEmail = "test@example.com";
        when(userRepository.findByEmail(existingEmail)).thenReturn(Optional.of(testUser));

        // ACT
        UserDetails userDetails = userDetailsService.loadUserByUsername(existingEmail);

        // ASSERT
        assertNotNull(userDetails);
        assertEquals(testUser.getEmail(), userDetails.getUsername());
        assertEquals(testUser.getPassword(), userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().isEmpty());

        verify(userRepository, times(1)).findByEmail(existingEmail);
    }

    @Test
    @DisplayName("Test 2: loadUserByUsername - Should throw UsernameNotFoundException for non-existing email")
    void loadUserByUsername_NonExistingUser_ThrowsUsernameNotFoundException() {
        // ARRANGE
        String nonExistingEmail = "nonexistent@example.com";
        when(userRepository.findByEmail(nonExistingEmail)).thenReturn(Optional.empty());

        // ACT & ASSERT
        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername(nonExistingEmail);
        });

        String expectedMessage = "User not found with email: " + nonExistingEmail;
        assertEquals(expectedMessage, exception.getMessage());

        verify(userRepository, times(1)).findByEmail(nonExistingEmail);
    }
}