package com.fawry.moviesapi.security;

import com.fawry.moviesapi.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;



public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (user.getRole().equals("Admin")) {
            return List.of(new SimpleGrantedAuthority("Admin"));
        } else if (user.getRole().equals("User")) {

            return List.of(new SimpleGrantedAuthority("User"));
        } else {
            return List.of();
        }

    }
}
