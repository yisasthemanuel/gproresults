package org.jlobato.gpro.results;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class GproResultsAPIApplication.
 */
@SpringBootApplication
@ComponentScan(basePackages = "org.jlobato.gpro")
@MapperScan("org.jlobato.gpro.dao.mybatis.mappers")
@Slf4j
public class GproResultsAPIApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(GproResultsAPIApplication.class, args);
		log.info("GproResultsAPIApplication is UP!!!");
	}

}
