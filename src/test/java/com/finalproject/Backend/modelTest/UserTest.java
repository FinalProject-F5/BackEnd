package com.finalproject.Backend.modelTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.finalproject.Backend.model.Country;
import com.finalproject.Backend.model.User;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserTest {

    @Test
    @DisplayName("Test 1: NoArgsConstructor and Setters - Should create user and set properties")
    void noArgsConstructorAndSetters_ShouldCreateUserAndSetProperties() {
        // ARRANGE
        User user = new User();

        // ACT
        user.setId(1L);
        user.setName("Test User");
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setInterests("Reading, Hiking");
        user.setBio("A test user bio.");
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        // ASSERT
        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("Test User", user.getName());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals("Reading, Hiking", user.getInterests());
        assertEquals("A test user bio.", user.getBio());
        assertEquals(now, user.getCreatedAt());
        assertEquals(now, user.getUpdatedAt());
    }

    @Test
    @DisplayName("Test 2: AllArgsConstructor and Getters - Should create user with all properties")
    void allArgsConstructorAndGetters_ShouldCreateUserWithAllProperties() {
        // ARRANGE
        Long id = 2L;
        String name = "Another User";
        String email = "another@example.com";
        String password = "securePassword";
        Country country = new Country();
        country.setCode("CA");
        country.setName("Canada");
        String interests = "Gaming, Cooking";
        String bio = "Another test user bio.";
        LocalDateTime createdAt = LocalDateTime.now().minusDays(1);
        LocalDateTime updatedAt = LocalDateTime.now();

        // ACT
        User user = new User(id, name, email, password, country, interests, bio, createdAt, updatedAt);

        // ASSERT
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(country, user.getCountry());
        assertEquals(interests, user.getInterests());
        assertEquals(bio, user.getBio());
        assertEquals(createdAt, user.getCreatedAt());
        assertEquals(updatedAt, user.getUpdatedAt());
    }
}