package com.finalproject.Backend.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
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
    private String host;

    @NotBlank
    @Size(max = 100)
    private String category;

    @Size(max = 255)
    private String description;

    @Size(max = 100)
    private String location;

    @NotBlank
    @Size(max = 100)
    private String price;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long userId;  
}
