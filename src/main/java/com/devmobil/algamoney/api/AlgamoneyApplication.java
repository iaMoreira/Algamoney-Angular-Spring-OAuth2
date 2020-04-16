package com.devmobil.algamoney.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.devmobil.algamoney.api.config.ApplicationProperty;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperty.class)
public class AlgamoneyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgamoneyApplication.class, args);
	}

}
