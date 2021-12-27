package me.faouzi.citiesgalleryrestapi.controller;

import java.util.List;

import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import me.faouzi.citiesgalleryrestapi.model.entity.City;
import me.faouzi.citiesgalleryrestapi.service.CityService;

@Path("/")
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@GET
	@Path("cities/{pageNumber}/{pageSize}")
	@Produces("application/json")
	
	public List<City> getCities(@PathParam("pageNumber") int pageNumber, @PathParam("pageSize") int pageSize) throws Exception{
		return cityService.getCities(pageNumber,pageSize);
	}

	@GET
	@Path("cities")
	@Produces("application/json")
	
	public List<City> getCities() throws Exception{
		return cityService.getCities(5,60);
	}


}
