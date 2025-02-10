package com.cts.multiplexmoviebooking.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.multiplexmoviebooking.dtos.LoginDTO;
import com.cts.multiplexmoviebooking.models.User;
import com.cts.multiplexmoviebooking.repos.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired private UserRepository repo;
	@Autowired private PasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = repo.findByEmail(email).get();
		System.out.println("user load "+user);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username or email: " + email);
		}


		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority(user.isAdmin() ? "ADMIN":"USER"));


		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}
	
	public void saveUser(User user) {
		String password=encoder.encode(user.getPassword());
		user.setPassword(password);
		repo.save(user);
	}
	
	public List<User> listall(){
		return repo.findAll();
	}
	
	public User findById(int id) {
		return repo.getById(id);
	}

	public Optional<User> findByEmailId(String email) {
		return repo.findByEmail(email);
	}

	public boolean CheckIfExists(String email){
		return repo.findByEmail(email).isPresent();
	}
	
	public User validate(LoginDTO dto) {
		Optional<User> user=repo.findByEmail(dto.getUserid());
		if(user.isPresent() && user.get().getPassword().equals(dto.getPassword())) {
			return user.get();
		}
		return null;
	}
}
