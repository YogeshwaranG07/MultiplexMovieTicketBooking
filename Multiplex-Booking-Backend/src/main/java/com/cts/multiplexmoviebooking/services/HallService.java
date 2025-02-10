package com.cts.multiplexmoviebooking.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.multiplexmoviebooking.dtos.HallCapacityDTO;
import com.cts.multiplexmoviebooking.models.Hall;
import com.cts.multiplexmoviebooking.models.HallCapacity;
import com.cts.multiplexmoviebooking.repos.HallCapacityRepository;
import com.cts.multiplexmoviebooking.repos.HallRepository;

@Service
public class HallService {
	@Autowired private HallRepository repo;
	@Autowired private HallCapacityRepository hcrepo;
	public void saveCapacity(HallCapacityDTO dto) {
		HallCapacity hc=new HallCapacity();
		BeanUtils.copyProperties(dto, hc);
		Hall hall=repo.getById(dto.getHallId());
		hc.setHall(hall);
		
		HallCapacity hcc= hcrepo.save(hc);
		List<HallCapacity> hcs= hall.getHallcapacity();
		hcs.add(hcc);
		hall.setHallcapacity(hcs);
		repo.save(hall);
	}
	
	public void deleteSeat(int id) {
		HallCapacity hc=hcrepo.getById(id);
		Hall hall=hc.getHall();
		List<HallCapacity> hcs= hall.getHallcapacity();
		hcs.remove(hc);
		repo.save(hall);
		hcrepo.delete(hc);
	}

	public void save(Hall hall) {
		repo.save(hall);
	}

	public List<Hall> listall(){
		return repo.findAll();
	}

	public Hall findById(int id) {
		return repo.getById(id);
	}

	public void deleteHall(int id) {
		repo.delete(repo.getById(id));
	}
}
