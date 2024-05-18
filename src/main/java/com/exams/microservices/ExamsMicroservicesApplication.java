package com.exams.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EntityScan({"com.commonsexamns.entity"})
public class ExamsMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamsMicroservicesApplication.class, args);
	}

}
