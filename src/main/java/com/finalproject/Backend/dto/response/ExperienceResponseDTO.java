package com.finalproject.Backend.dto.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceResponseDTO {

    private Long id;
    private String title;
    private String location;
    private String category;
    private String description;
    private String duration;
    private BigDecimal price;
   // private String itinerary;
   // private String observation;
    private String host;
    private String email;
    private String mobile;
    private String addInfo;
    private Long userId;
    private String userName;
    
    private List<String> imageUrls;
    
}