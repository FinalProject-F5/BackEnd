package com.finalproject.Backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

   @NotBlank
   @Size(min = 8, max = 120)
   @Pattern(
      regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&^()_+=-]).{8,}$",
      message = "Password must include uppercase, lowercase, number, and special character"
   )
   private String password;

   @NotBlank
   @Size(max = 20)
   private String countryCode;
   
   // @Size(max = 200)
   // private String interest;

   // @Size(max = 200)
   // private String bio;
 
}