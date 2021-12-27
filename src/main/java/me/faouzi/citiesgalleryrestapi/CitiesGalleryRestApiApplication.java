package me.faouzi.citiesgalleryrestapi;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CitiesGalleryRestApiApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		//SpringApplication.run(CitiesGalleryRestApiApplication.class, args);
        new CitiesGalleryRestApiApplication().configure(new SpringApplicationBuilder(CitiesGalleryRestApiApplication.class)).run(args);

	}

}
