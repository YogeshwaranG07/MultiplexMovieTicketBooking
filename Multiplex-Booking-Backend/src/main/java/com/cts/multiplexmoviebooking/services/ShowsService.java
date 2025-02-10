package com.cts.multiplexmoviebooking.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.multiplexmoviebooking.dtos.SearchDTO;
import com.cts.multiplexmoviebooking.dtos.ShowDTO;
import com.cts.multiplexmoviebooking.models.Shows;
import com.cts.multiplexmoviebooking.repos.ShowsRepository;

@Service
public class ShowsService {

	@Autowired private ShowsRepository repo; 
	@Autowired private MovieService mservice;
	@Autowired private HallService hservice;
	@Autowired private SearchSpecification ssp;
	
	public void save(ShowDTO dto) {
		Shows show=new Shows();
		BeanUtils.copyProperties(dto, show);
		show.setHall(hservice.findById(dto.getHallId()));
		show.setMovie(mservice.findById(dto.getMovieId()));
		repo.save(show);
	}
	
	public List<Shows> listall(){
		return repo.activeShows();
	}
	
	public List<Shows> todayShows(){
		return repo.todaysShow();
	}
	
	public List<Shows> searchShows(SearchDTO dto){
		return repo.findAll(ssp.getfilteredShows(dto));
	}
	
	public Shows findById(int id) {
		return repo.getById(id);
	}
	
	public void deleteShow(int id) {
		repo.delete(repo.getById(id));
	}
}
