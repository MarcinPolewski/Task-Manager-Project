package com.marcin.jacek.polewski.Task_Manager_Project;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import javafx.application.Application;

/**
 * Main class serves as the entry point for the Spring Boot and JavaFX application.
 * It is annotated with @SpringBootApplication, which triggers Spring Boot's auto-configuration and component scanning.
 */
@SpringBootApplication
public class Main {

	/**
	 * The main method is the entry point for the Java application.
	 * Instead of using the traditional SpringApplication.run() method to start Spring Boot,
	 * this method launches the JavaFX application.
	 *
	 * @param args Command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		// Normally, Spring Boot is started with SpringApplication.run(),
		// but here we use Application.launch() to integrate JavaFX with Spring.

		// SpringApplication.run(Main.class, args); // Commented out to prevent standard Spring Boot startup

		// Launch the JavaFX application, which is integrated with Spring in the JavaFxApplication class
		Application.launch(JavaFXApplication.class, args);
	}

}
