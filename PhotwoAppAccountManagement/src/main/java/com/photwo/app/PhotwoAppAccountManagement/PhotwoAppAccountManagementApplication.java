package com.photwo.app.PhotwoAppAccountManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PhotwoAppAccountManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotwoAppAccountManagementApplication.class, args);
	}

}
