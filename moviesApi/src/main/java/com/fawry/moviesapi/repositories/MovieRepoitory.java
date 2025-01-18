package com.fawry.moviesapi.repositories;

import com.fawry.moviesapi.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepoitory extends JpaRepository<Movie,String> {
    @Query("""
    SELECT m from Movie m where m.title like %:title%
    """)
    public Page<Movie> findAllByTitleContaining(String title , Pageable pageable);
}
