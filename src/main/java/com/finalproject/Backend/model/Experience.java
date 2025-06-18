package com.finalproject.Backend.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
import jakarta.persistence.Column;

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

    @Column(columnDefinition = "TEXT")
    @Size(max = 2000)
   private String itinerary;

   @Column(columnDefinition = "TEXT")
    @Size(max = 2000)
    private String observation;

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

    @OneToMany(mappedBy = "experience", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExperienceImage> images = new ArrayList<>();
}
