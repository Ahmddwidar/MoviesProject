package com.fawry.moviesapi.config;

import com.fawry.moviesapi.services.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

   private final CustomUserDetailsService customUserDetailsService;

   @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
               .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/home/admin",
                                "/dashboard/movies/omdb-fetch",
                                "/dashboard/movies/add",
                                "/dashboard/movies/add")
                        .hasAnyAuthority("Admin")
                        .requestMatchers("/dashboard/movies").authenticated()
                        .anyRequest().permitAll()
                ).csrf((csrf)->csrf.disable())
                .formLogin(form-> form
                        .successHandler(authenticationSuccessHandler())
                        .permitAll());

        return http.build();
    }
    private AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (HttpServletRequest request, HttpServletResponse response, Authentication authentication) -> {
            // Check the roles of the authenticated user
            boolean isAdmin = authentication.getAuthorities().contains(new SimpleGrantedAuthority("Admin"));

            boolean isUser = authentication.getAuthorities().contains(new SimpleGrantedAuthority("User"));

            if (isAdmin) {
                // Forward Admin users to the admin dashboard
                response.sendRedirect("/home/admin");
            } else if (isUser) {
                // Forward User to the user dashboard
                response.sendRedirect("/home/user");
            } else {
                // Redirect to a default page if no specific role matches
                response.sendRedirect("/home");
            }
        };
    }


//                .exceptionHandling(ex -> ex
//                        .authenticationEntryPoint((request, response, authException) -> {
//                            System.out.println("Blocked access to: " + request.getRequestURI());
//                            System.out.println("Reason: " + authException.getMessage());
//                            if( request.getRequestURI().equals("/checkout"))
//                                response.sendRedirect("/auth/login");
//                            else response.sendRedirect("/error");
//                        })
//                ).build();
//       // return http.build();


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }





}