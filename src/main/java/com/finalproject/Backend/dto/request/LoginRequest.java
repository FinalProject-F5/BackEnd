package com.finalproject.Backend.dto.request; 

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequest {
   @NotBlank
   @Email
   private String email;

   @NotBlank
   @Size(min = 8, max = 120)
   @Pattern(
      regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&^()_+=-]).{8,}$",
      message = "Password must include uppercase, lowercase, number, and special character"
   )
   private String password;
}