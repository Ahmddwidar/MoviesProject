package com.fawry.moviesapi.services;

import com.fawry.moviesapi.entities.User;
import com.fawry.moviesapi.repositories.UserRepository;
import com.fawry.moviesapi.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof CustomUserDetails) {
                CustomUserDetails customUserDetails = (CustomUserDetails) principal;
                return userRepository.findByEmail(customUserDetails.getUsername()).orElse(null);
            }
        }
        return null;
    }
}
