package com.zzdd.smallspending;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = com.zzdd.smallspending.chat.MongoDBRepository.class)
public class SmallSpendingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmallSpendingApplication.class, args);
	}

}
