package com.finalproject.Backend.dto.response;
 
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponse {
   private String token;
   private String type = "Bearer";
   private Long id;
   private String name;
   private String email; 
}