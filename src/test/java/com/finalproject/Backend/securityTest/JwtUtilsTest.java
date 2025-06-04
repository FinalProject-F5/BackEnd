package com.finalproject.Backend.securityTest; 
import com.finalproject.Backend.repository.UserRepository;
import com.finalproject.Backend.security.JwtUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils; 

import java.security.Key;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class JwtUtilsTest {

    @Mock 
    private UserRepository userRepository;


    private JwtUtils jwtUtils;

    
    private final String testSecret = "yourTestSecretKeyShouldBeLongEnoughToSuccesfullyProtectYouHS256AndBase64Encoded"; 
    private final int testExpirationMs = 3600000; 

    @BeforeEach
    void setUp() {
        
        jwtUtils = new JwtUtils(userRepository);

       
        ReflectionTestUtils.setField(jwtUtils, "jwtSecret", testSecret);
        ReflectionTestUtils.setField(jwtUtils, "jwtExpirationMs", testExpirationMs);
    }

    private String generateValidTestToken(String subject, Date expirationDate) {
        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(testSecret));
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Test
    @DisplayName("Test 1: validateJwtToken - Valid TOKEN return true")
    void validateJwtToken_ValidToken_ReturnsTrue() {
        // ARRANGE
        // Genera un token que expira en el futuro
        Date futureDate = new Date(System.currentTimeMillis() + testExpirationMs);
        String validToken = generateValidTestToken("testuser@example.com", futureDate);

        // ACT
        boolean isValid = jwtUtils.validateJwtToken(validToken);

        // ASSERT
        assertTrue(isValid, "The Token should be validated correctly");
    }

    @Test
    @DisplayName("Test 2: validateJwtToken - Token invalid (expired) should return false")
    void validateJwtToken_ExpiredToken_ReturnsFalse() {
        // ARRANGE
       
        Date pastDate = new Date(System.currentTimeMillis() - 1000); 
        String expiredToken = generateValidTestToken("expireduser@example.com", pastDate);

        // ACT
        boolean isValid = jwtUtils.validateJwtToken(expiredToken);

        // ASSERT
        assertFalse(isValid, "The expired TOKEN should not be validated ");
    }

}