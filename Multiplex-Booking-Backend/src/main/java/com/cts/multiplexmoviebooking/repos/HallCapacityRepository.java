package com.cts.multiplexmoviebooking.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.multiplexmoviebooking.models.Hall;
import com.cts.multiplexmoviebooking.models.HallCapacity;

@Repository
public interface HallCapacityRepository extends JpaRepository<HallCapacity, Integer> {
	
	List<HallCapacity> findByHall(Hall hall);

}
