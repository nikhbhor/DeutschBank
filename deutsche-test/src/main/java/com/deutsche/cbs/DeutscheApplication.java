package com.deutsche.cbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.deutsche.cbs","com.deutsche.cbs.service","com.deutsche.cbs.helper"})
public class DeutscheApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeutscheApplication.class, args);

	}

}
