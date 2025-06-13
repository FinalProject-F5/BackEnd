package com.finalproject.Backend.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "experiences")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

   // @Size(min = 300)
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



    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
