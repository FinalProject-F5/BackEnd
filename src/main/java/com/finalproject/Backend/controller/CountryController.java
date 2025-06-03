package com.finalproject.Backend.controller;
 
import com.finalproject.Backend.model.Country; 
import com.finalproject.Backend.service.CountryService; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*;
 
import java.util.List; 

@RestController
@RequestMapping("/api/countries")
@CrossOrigin(origins = "http://localhost:5173")
public class CountryController {

   CountryService countryService;

   public CountryController(CountryService countryService) {
         this.countryService = countryService; 
   }

   @GetMapping("/all")
   public ResponseEntity<List<Country>> getAllCountries() {
       List<Country> countries = countryService.getAllCountries();  
       return ResponseEntity.ok(countries);
   }
   

}
