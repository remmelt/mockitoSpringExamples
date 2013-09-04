package com.remmelt.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.external.database.DatabaseAccess;
import com.external.database.impl.DatabaseAccessImpl;

@Configuration
@ComponentScan("com.remmelt.example")
public class ApplicationConfig {

	// Since the "external" class DatabaseAccess is not marked as @Service or @Repository we're going to @Bean it explicitly
	@Bean
	public DatabaseAccess databaseAccess() {
		return new DatabaseAccessImpl();
	}

}
