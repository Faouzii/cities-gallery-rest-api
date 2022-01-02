package me.faouzi.citiesgalleryrestapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class CitiesGalleryRestApiApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
        new CitiesGalleryRestApiApplication().configure(new SpringApplicationBuilder(CitiesGalleryRestApiApplication.class)).run(args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


}
