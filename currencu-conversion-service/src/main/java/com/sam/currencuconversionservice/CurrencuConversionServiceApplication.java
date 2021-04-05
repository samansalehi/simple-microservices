package com.sam.currencuconversionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.sam.currencuconversionservice")
public class CurrencuConversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencuConversionServiceApplication.class, args);
	}

}