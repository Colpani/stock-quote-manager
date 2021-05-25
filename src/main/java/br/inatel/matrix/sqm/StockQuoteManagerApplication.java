package br.inatel.matrix.sqm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@SpringBootApplication
@EnableCaching
public class StockQuoteManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockQuoteManagerApplication.class, args);
	}

}
