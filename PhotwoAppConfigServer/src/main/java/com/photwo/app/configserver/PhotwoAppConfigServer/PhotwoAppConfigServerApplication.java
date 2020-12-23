package com.photwo.app.configserver.PhotwoAppConfigServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class PhotwoAppConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotwoAppConfigServerApplication.class, args);
	}

}
