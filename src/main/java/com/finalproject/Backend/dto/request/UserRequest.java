package com.finalproject.Backend.dto.request;
  
import com.finalproject.Backend.model.Country;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequest {
   @NotBlank
   @Size(max = 50)
   private String name;

   @NotBlank
   @Size(max = 120)
   @Email
   private String email;

   @Size(max = 120)
   private String password;

   @NotBlank
   @Size(max = 20)
   private String countryCode;
   
   // @Size(max = 200)
   // private String interest;

   // @Size(max = 200)
   // private String bio;
 
}