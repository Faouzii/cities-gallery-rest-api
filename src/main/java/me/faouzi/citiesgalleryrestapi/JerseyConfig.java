package me.faouzi.citiesgalleryrestapi;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import me.faouzi.citiesgalleryrestapi.controller.CityController;
 
@Component
public class JerseyConfig extends ResourceConfig 
{
    public JerseyConfig() 
    {
    	register(CorsFilter.class);
        register(CityController.class);
    }
}