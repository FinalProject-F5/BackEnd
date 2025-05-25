package com.finalproject.Backend.repository;

import com.finalproject.Backend.model.Country; 
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
}
