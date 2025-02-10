package com.cts.multiplexmoviebooking.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.multiplexmoviebooking.models.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{	
}
