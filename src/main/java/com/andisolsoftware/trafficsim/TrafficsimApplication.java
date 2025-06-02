package com.andisolsoftware.trafficsim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.andisolsoftware.trafficsim.Main;

@SpringBootApplication
public class TrafficsimApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrafficsimApplication.class, args);
		System.setProperty("java.awt.headless", "false"); //Disables headless
		Main.main(args);
	}

}
