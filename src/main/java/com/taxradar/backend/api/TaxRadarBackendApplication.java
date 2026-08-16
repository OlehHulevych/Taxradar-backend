package com.taxradar.backend.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.taxradar.backend.domain.entities")
@SpringBootApplication(scanBasePackages = "com.taxradar.backend.domain")
@EnableJpaRepositories("com.taxradar.backend.infrastructure.repositories")
public class TaxRadarBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxRadarBackendApplication.class, args);
	}

}
