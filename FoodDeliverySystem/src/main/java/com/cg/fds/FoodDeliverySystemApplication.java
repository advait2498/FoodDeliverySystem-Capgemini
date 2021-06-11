package com.cg.fds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages={"com.cg.fds","com.cg.fds.service.impl"})
@SpringBootApplication
public class FoodDeliverySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodDeliverySystemApplication.class, args);
	}

}
