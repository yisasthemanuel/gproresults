package org.jlobato.gpro.results;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:/config.properties")
@ComponentScan(basePackages = "org.jlobato.gpro")
@ImportResource("classpath:/spring-database.xml")
public class GproResultsAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(GproResultsAPIApplication.class, args);
	}

}
