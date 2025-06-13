package com.finalproject.Backend.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceRequestDTO {

     @NotBlank
    @Size(max = 100)
    private String title;

    @NotBlank
    @Size(max = 100)
    private String location;

    @NotBlank
    private String category;

    @NotBlank
    @Size(min = 100)
    private String description;


  
    private String duration;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;

  //  @Size(min = 300)
   // private String itinerary;

   // @Size(min = 300)
   // private String observation;

    @NotBlank
    @Size(max = 100)
    private String host;

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    
    @Size(max = 20)
    private String mobile;

    
    private String addInfo;

   
}
