package com.cts.multiplexmoviebooking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.cts.multiplexmoviebooking.models.User;
import com.cts.multiplexmoviebooking.services.UserService;
import com.cts.multiplexmoviebooking.utils.FileUploadProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    FileUploadProperties.class
})
public class MultiplexMovieBookingBackendApplication {
	
	private static final Logger log = LoggerFactory.getLogger(MultiplexMovieBookingBackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MultiplexMovieBookingBackendApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(UserService srv) {
	    return (args) -> {
	    	if(srv.listall().size()==0) {
	    		User admin=new User();
	    		admin.setEmail("admin");
	    		admin.setPassword("admin");
	    		admin.setUserName("Administrator");
	    		admin.setAdmin(true);;
	    		srv.saveUser(admin);
	    		log.info("Admin user created successfully");
	    	}
	    };
	}

}
