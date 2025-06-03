package com.finalproject.Backend.security; 

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.finalproject.Backend.model.User;
import com.finalproject.Backend.repository.UserRepository;
 

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
   private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

   @Value("${app.jwt.secret}")
   private String jwtSecret;

   @Value("${app.jwt.expiration}")
   private int jwtExpirationMs;

   private UserRepository userRepository;

   public JwtUtils(@Autowired UserRepository userRepository) {
       this.userRepository = userRepository;
   }

   public String generateJwtToken(Authentication authentication) {
       UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
       String email = userPrincipal.getUsername();
       
       User user = userRepository.findByEmail(email)
               .orElseThrow(() -> new RuntimeException("User not found when generating token"));
       
       Map<String, Object> claims = new HashMap<>();
       claims.put("id", user.getId());
       claims.put("username", user.getName());
       claims.put("email", user.getEmail());
       claims.put("countryOfOrigin", user.getCountry());

       return Jwts.builder()
               .setClaims(claims)
               .setSubject(email)
               .setIssuedAt(new Date())
               .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
               .signWith(key(), SignatureAlgorithm.HS256)
               .compact();
   }

   private Key key() {
       return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
   }

  
    
   public Long getUserIdFromJwtToken(String token) {
       return ((Number) Jwts.parserBuilder()
               .setSigningKey(key())
               .build()
               .parseClaimsJws(token)
               .getBody()
               .get("id")).longValue();
   }
   
   public String getNameFromJwtToken(String token) {
       return (String) Jwts.parserBuilder()
               .setSigningKey(key())
               .build()
               .parseClaimsJws(token)
               .getBody()
               .get("name");
   }
   
   
   public boolean validateJwtToken(String authToken) {
       try {
           Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(authToken);
           return true;
       } catch (MalformedJwtException e) {
           logger.error("Invalid JWT token: {}", e.getMessage());
       } catch (ExpiredJwtException e) {
           logger.error("JWT token expired: {}", e.getMessage());
       } catch (UnsupportedJwtException e) {
           logger.error("JWT token not supported: {}", e.getMessage());
       } catch (IllegalArgumentException e) {
           logger.error("JWT claims string is empty: {}", e.getMessage());
       }

       return false;
   }
}