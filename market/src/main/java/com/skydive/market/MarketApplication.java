package com.skydive.market;

import com.skydive.market.model.Registration;
import com.skydive.market.service.JsonReaderService;
import com.skydive.market.service.RegistrationService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class MarketApplication {

	public static void main(String[] args) {
//		RegistrationService registrationService = new RegistrationService();
//		List<Registration> registrations = new JsonReaderService().getTestData("market/src/main/resources/testData.json");
//		registrationService.saveAll(registrations);
		SpringApplication.run(MarketApplication.class, args);
	}
}
