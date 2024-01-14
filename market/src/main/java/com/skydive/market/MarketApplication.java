package com.skydive.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MarketApplication {

	public static void main(String[] args) {
//		RegistrationService registrationService = new RegistrationService();
//		List<Registration> registrations = new JsonReaderService().getTestData("market/src/main/resources/testData.json");
//		registrationService.saveAll(registrations);
		SpringApplication.run(MarketApplication.class, args);
	}
}
