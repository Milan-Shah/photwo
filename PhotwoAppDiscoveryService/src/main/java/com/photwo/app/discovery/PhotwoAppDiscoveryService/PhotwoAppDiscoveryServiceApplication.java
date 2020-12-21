package com.photwo.app.discovery.PhotwoAppDiscoveryService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PhotwoAppDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotwoAppDiscoveryServiceApplication.class, args);
	}

}
