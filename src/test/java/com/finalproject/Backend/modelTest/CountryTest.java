package com.finalproject.Backend.modelTest;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.finalproject.Backend.model.Country;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CountryTest {

    @Test
    @DisplayName("Test 1: NoArgsConstructor and Setters - Should create country and set properties")
    void noArgsConstructorAndSetters_ShouldCreateCountryAndSetProperties() {
        // ARRANGE & ACT
        Country country = new Country();  
        country.setCode("CO");
        country.setName("Colombia");

        // ASSERT
        assertNotNull(country);
        assertEquals("CO", country.getCode());
        assertEquals("Colombia", country.getName());
    }

    @Test
    @DisplayName("Test 2: AllArgsConstructor and Getters - Should create country with all properties")
    void allArgsConstructorAndGetters_ShouldCreateCountryWithAllProperties() {
        // ARRANGE & ACT
        Country country = new Country("ES", "Spain");  

        // ASSERT
        assertNotNull(country);
        assertEquals("ES", country.getCode());
        assertEquals("Spain", country.getName());
    }
}