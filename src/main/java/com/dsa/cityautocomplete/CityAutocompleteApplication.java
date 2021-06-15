package com.dsa.cityautocomplete;

import com.dsa.cityautocomplete.datastructures.IMTester;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CityAutocompleteApplication {

	public static void main(String[] args) {
		IMTester.main("a");
		SpringApplication.run(CityAutocompleteApplication.class, args);
	}

}
