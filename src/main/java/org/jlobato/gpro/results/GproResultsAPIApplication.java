package org.jlobato.gpro.results;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * The Class GproResultsAPIApplication.
 */
@SpringBootApplication
@ComponentScan(basePackages = "org.jlobato.gpro")
@ImportResource("classpath:/spring-database.xml")
public class GproResultsAPIApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(GproResultsAPIApplication.class, args);
	}

}
