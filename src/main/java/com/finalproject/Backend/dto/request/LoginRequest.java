package com.finalproject.Backend.dto.request; 

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
<<<<<<< HEAD
import jakarta.validation.constraints.Size;

=======
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
>>>>>>> ff4d9802a56c8b018326ec99b1fa678a707c7315
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequest {
   @NotBlank
   @Email
   private String email;

   @NotBlank
<<<<<<< HEAD
   @Size(min = 8)
=======
   @Size(min = 8, max = 120)
   @Pattern(
      regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&^()_+=-]).{8,}$",
      message = "Password must include uppercase, lowercase, number, and special character"
   )
>>>>>>> ff4d9802a56c8b018326ec99b1fa678a707c7315
   private String password;
}