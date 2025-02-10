package com.cts.multiplexmoviebooking.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.multiplexmoviebooking.dtos.BookingDTO;
import com.cts.multiplexmoviebooking.dtos.ShowCheckDTO;
import com.cts.multiplexmoviebooking.models.Booking;
import com.cts.multiplexmoviebooking.repos.BookingRepository;

@Service
public class BookingService {

	@Autowired private BookingRepository repo;
	@Autowired private ShowsService sservice;
	@Autowired private UserService uservice;
	
	public void save(BookingDTO dto) {
		Booking bk=new Booking();
		bk.setShowDate(dto.getBookDate());
		bk.setNoOfSeats(dto.getNoOfSeats());
		bk.setCost(dto.getCost());
		bk.setShow(sservice.findById(dto.getShowId()));
		bk.setUser(uservice.findById(dto.getUserId()));
		repo.save(bk);
	}
	
	public List<Booking> listall(){
		return repo.findAll();
	}
	
	public List<Booking> alluserbooking(int id){
		return repo.findByUser(uservice.findById(id));
	}
	
	public Booking findById(int id) {
		return repo.getById(id);
	}
	
	public List<Booking> allOccupiedBookings(ShowCheckDTO dto){
		return repo.findByShowShowIdAndShowDate(dto.getShowid(), dto.getDate());
	}
	
	public void delete(int id) {
		Booking bk=repo.getById(id);
		bk.setStatus("Cancelled");
		bk.setCancelCharges((float) (bk.getCost()*.20));
		bk.setCost(0);
		repo.save(bk);
	}
}
