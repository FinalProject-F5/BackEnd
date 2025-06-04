package com.finalproject.Backend;

import com.finalproject.Backend.model.User;
import com.finalproject.Backend.repository.UserRepository;
import com.finalproject.Backend.service.UserService;
import com.finalproject.Backend.repository.CountryRepository; 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder; 

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;  

    @Mock
    private CountryRepository countryRepository;  

    @InjectMocks
    private UserService userService;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
         
        user1 = new User();
        user1.setId(1L);
        user1.setName("Mariuxi");
        user1.setEmail("mariuxi@example.com");
       

        user2 = new User();
        user2.setId(2L);
        user2.setName("Tobi");
        user2.setEmail("tobi@example.com");
    }

    @Test
    @DisplayName("Test 1: getAllUsers -SHould show a list of all users")
    void getAllUsers_ReturnsListOfUsers() {
        // ARRANGE
       
        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users);

        // ACT
        List<User> result = userService.getAllUsers();

        // ASSERT
         
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(user1));
        assertTrue(result.contains(user2));

         
        verify(userRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test 2: getUserById - Should show the existence of one of the users")
    void getUserById_ExistingUser_ReturnsUser() {
        // ARRANGE
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(user1));

        // ACT
        User result = userService.getUserById(userId);

        // ASSERT
        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals(user1.getName(), result.getName());
        assertEquals(user1.getEmail(), result.getEmail());

       
        verify(userRepository, times(1)).findById(userId);
    }
    
}