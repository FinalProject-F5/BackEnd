package com.finalproject.Backend.service;

import com.finalproject.Backend.model.Country; 
import com.finalproject.Backend.repository.CountryRepository; 
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    CountryRepository countryServiceRepository;

    public CountryService(CountryRepository countryServiceRepository) {
        this.countryServiceRepository = countryServiceRepository;
    }

    public List<Country> getAllCountries() {
        return countryServiceRepository.findAll();
    }

}
