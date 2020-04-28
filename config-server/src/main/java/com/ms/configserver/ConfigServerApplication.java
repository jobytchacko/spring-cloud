package com.ms.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author joby.chacko
 * @since 20 - 04 - 2020
 * @version 1.0
 * This service is used to keep the configuration file of different services for different profiles
 * Services can access the configuration via config server
 * The location of the git repo where configurations are kept is specified in application properties
 * EnableConfigServer helps to this service to act as configuration service
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);	
	}
}
