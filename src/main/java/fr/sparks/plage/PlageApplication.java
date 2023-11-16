package fr.sparks.plage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // Permet d'activer le scheduling
public class PlageApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlageApplication.class, args);
	}

}
