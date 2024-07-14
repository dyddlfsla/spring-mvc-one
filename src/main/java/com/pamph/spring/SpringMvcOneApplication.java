package com.pamph.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SpringMvcOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcOneApplication.class, args);
	}

}
