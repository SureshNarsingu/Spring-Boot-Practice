package com.movie;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;

@OpenAPIDefinition(info = @Info(title = "Movie Booking Api", version = "2.0", description = "This API is ued to •Browse Movies •Book Tickets •View Booking History •Search and Filter"))
@SpringBootApplication
@Slf4j
@ComponentScan(basePackages ={"com.movie"})
public class CinemaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaProjectApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") // Adjust the mapping pattern as per your requirements
					.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Set the allowed HTTP methods
					.allowedHeaders("*") // Set the allowed headers
					.allowedOrigins("http://localhost:3000") // Set the allowed origins
					.allowCredentials(true); // Allow credentials, if needed
			}
		};
	}
}
