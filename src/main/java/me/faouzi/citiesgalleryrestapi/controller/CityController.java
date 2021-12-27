package me.faouzi.citiesgalleryrestapi.controller;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import me.faouzi.citiesgalleryrestapi.model.dto.CityListResponseDto;
import me.faouzi.citiesgalleryrestapi.service.CityService;

@Path("/cities")
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@GET
	@Produces("application/json")
	public Response getCities(@QueryParam("page") int pageNumber, @QueryParam("size") int pageSize) throws Exception{
		CityListResponseDto response =  cityService.getCities(pageNumber,pageSize);
		return Response.status(200).entity(response).build();
	}

	

}
