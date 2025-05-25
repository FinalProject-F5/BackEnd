package com.finalproject.Backend.security; 

import com.finalproject.Backend.model.User;
import com.finalproject.Backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService { 
   
   @Autowired
   UserRepository userRepository;

   @Override
   @Transactional
   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       System.out.println("Looking for user by email: " + email);
       
       User user = userRepository.findByEmail(email)
               .orElseThrow(() -> {
                   System.out.println("User not found with email: {}" + email);
                   return new UsernameNotFoundException("User not found with email: " + email);
               });
  
       List<SimpleGrantedAuthority> authorities = new ArrayList<>(); 
       
       return new org.springframework.security.core.userdetails.User(
               user.getEmail(),
               user.getPassword(),
               authorities);
   }
}