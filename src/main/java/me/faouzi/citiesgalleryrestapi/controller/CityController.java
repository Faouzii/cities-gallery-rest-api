package me.faouzi.citiesgalleryrestapi.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import me.faouzi.citiesgalleryrestapi.model.dto.CityDto;
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
	
	@GET
	@Path("/filter")
	@Produces("application/json")
	public Response searchByName(@QueryParam("name") String keyword, @QueryParam("page") int pageNumber, @QueryParam("size") int pageSize) throws Exception{
		CityListResponseDto response =  cityService.getCitiesByName(keyword,pageNumber,pageSize);
		System.out.println(response.getCities().size());
		return Response.status(200).entity(response).build();
	}

	@PUT
	@Path("/{uid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response updateCity(CityDto city,@PathParam("uid") String uid) throws Exception{
		CityDto dto = cityService.updateCity(city, uid);
		return Response.status(200).entity(dto).build();
	}

	@GET
	@Path("/status")
	public String test() {
		return "City Gallery Backend is up and running !";
	}
}











