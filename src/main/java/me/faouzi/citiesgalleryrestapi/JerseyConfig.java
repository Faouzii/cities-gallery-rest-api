package me.faouzi.citiesgalleryrestapi;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import me.faouzi.citiesgalleryrestapi.controller.AuthController;
import me.faouzi.citiesgalleryrestapi.controller.CityController;
import me.faouzi.citiesgalleryrestapi.exceptionmapper.AccessDeniedMapper;
import me.faouzi.citiesgalleryrestapi.exceptionmapper.BadCredentialsMapper;


@Component
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(CorsFilter.class);
		register(AccessDeniedMapper.class);
		register(BadCredentialsMapper.class);
		register(CityController.class);
		register(AuthController.class);
	}
}