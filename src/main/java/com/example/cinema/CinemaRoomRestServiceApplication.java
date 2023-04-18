package com.example.cinema;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class CinemaRoomRestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaRoomRestServiceApplication.class, args);
	}

}
