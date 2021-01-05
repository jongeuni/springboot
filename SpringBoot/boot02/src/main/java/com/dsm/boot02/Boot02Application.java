package com.dsm.boot02;

import com.dsm.boot02.persistence.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Boot02Application {

	public static void main(String[] args) {
		SpringApplication.run(Boot02Application.class, args);
	}

}
