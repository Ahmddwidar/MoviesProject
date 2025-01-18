package com.fawry.moviesapi.repositories;

import com.fawry.moviesapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
   @Query("""
     select u from User u where u.email= :email
      """)
   Optional<User> findByEmail(String email);
}
