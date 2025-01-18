//package com.fawry.moviesapi.repositories;
//import com.fawry.moviesapi.entities.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//
//public class OldCustomUserDetails implements UserDetails {
//
//    private User user;
//
//    public OldCustomUserDetails(User user) {
//
//        this.user = user;
//
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//
//        // Check the actual type of user and assign authorities accordingly
//
//        if (user.getRole().equals("Admin")) {
//
//            return List.of(new SimpleGrantedAuthority("ROLE"));
//
//        } else if (user.getRole().equals("User")) {
//
//            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
//
//        } else {
//
//            return Collections.emptyList(); // Default if no type matches
//
//        }
//
//    }
//
//    @Override
//
//    public String getPassword() {
//
//        return user.getPassword();
//
//    }
//
//    @Override
//
//    public String getUsername() {
//
//        return user.getEmail();
//
//    }
//}