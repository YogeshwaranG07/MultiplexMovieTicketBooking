package com.cts.multiplexmoviebooking.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cts.multiplexmoviebooking.dtos.MovieDTO;
import com.cts.multiplexmoviebooking.models.Movie;
import com.cts.multiplexmoviebooking.repos.MovieRepository;
import com.cts.multiplexmoviebooking.utils.StorageService;

@Service
public class MovieService {

	@Autowired private MovieRepository repo;
	@Autowired private StorageService storage;
	
	public void save(Movie dto, MultipartFile photo) {
		Movie movie=new Movie();
		BeanUtils.copyProperties(dto, movie);
		
		if(movie.getMovieId()==0) {
			String poster=storage.store(photo);
			movie.setPoster(poster);
		}else {
			if(photo!=null) {
				String poster=storage.store(photo);
				movie.setPoster(poster);				
			}else {
				Movie mm=repo.findById(movie.getMovieId()).get();
				movie.setPoster(mm.getPoster());
			}
		}
		repo.save(movie);
	}
	
	public List<Movie> listall(){
		return repo.findAll(Sort.by(Direction.DESC, "movieId"));
	}
	
	public Movie findById(int id) {
		return repo.findById(id).orElse(null);
	}
	
	public void deleteMovie(int id) {
		Movie movie=findById(id);
		storage.delete(movie.getPoster());
		repo.delete(repo.getById(id));
	}
}
