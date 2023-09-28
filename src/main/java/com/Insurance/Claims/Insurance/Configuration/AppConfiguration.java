package com.Insurance.Claims.Insurance.Configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("com.Insurance.Claims.Insurance")
public class AppConfiguration {
	@Bean
	DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl("jdbc:postgresql://test-db.ckjihemp0trh.us-east-1.rds.amazonaws.com:5432/postgres");
		driverManagerDataSource.setUsername("postgres");
		driverManagerDataSource.setPassword("Ap05cf3766");
		driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
		return driverManagerDataSource;
	}
}
