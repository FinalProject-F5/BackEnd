package com.finalproject.Backend.serviceTest; 

import com.finalproject.Backend.model.User; 
import com.finalproject.Backend.repository.UserRepository;
import com.finalproject.Backend.security.JwtUtils;
import com.finalproject.Backend.service.AuthService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager; 

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class) 
class AuthServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtUtils jwtUtils;
   
    @InjectMocks
    private AuthService authService;

    @Test
    @DisplayName("Test1: AuthService initialize and verifyToken works with a valid token")
    void basicVerifyToken_ValidTokenAndUserExists_ReturnsTrue() {
        // ARRANGE
        String testToken = "simple.test.token";
        String testEmail = "simple@example.com";

        
        when(jwtUtils.validateJwtToken(testToken)).thenReturn(true);
        when(jwtUtils.getNameFromJwtToken(testToken)).thenReturn(testEmail);

      
        User mockUser = new User();
    
        mockUser.setEmail(testEmail);
        when(userRepository.findByEmail(testEmail)).thenReturn(Optional.of(mockUser));

        // ACT
        boolean result = authService.verifyToken(testToken);

        // ASSERT
        assertTrue(result, "The token should be valid and the user should exist");

        // Verificaciones
        verify(jwtUtils, times(1)).validateJwtToken(testToken);
        verify(jwtUtils, times(1)).getNameFromJwtToken(testToken);
        verify(userRepository, times(1)).findByEmail(testEmail);
    }

    @Test
    @DisplayName("Test 2: verifyToken with invalid token")
    void basicVerifyToken_InvalidToken_ReturnsFalse() {
        // ARRANGE
        String invalidToken = "invalid.token";

        when(jwtUtils.validateJwtToken(invalidToken)).thenReturn(false);

        // ACT
        boolean result = authService.verifyToken(invalidToken);

        // ASSERT
        assertFalse(result, "The invalid token should not be validated.");
    }
}