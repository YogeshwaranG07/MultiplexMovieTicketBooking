package com.cts.multiplexmoviebooking.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.multiplexmoviebooking.models.Hall;

@Repository
public interface HallRepository extends JpaRepository<Hall, Integer> {

}
