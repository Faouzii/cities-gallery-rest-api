package me.faouzi.citiesgalleryrestapi.controller;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import me.faouzi.citiesgalleryrestapi.model.dto.JwtResponse;
import me.faouzi.citiesgalleryrestapi.model.entity.AuthUser;
import me.faouzi.citiesgalleryrestapi.service.UserService;

@Component
@Path("/auth")
public class AuthController {
	
	
	@Autowired
	UserService userService;


	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response processLogin(AuthUser requestUser) throws Exception {
		JwtResponse jwtResponse = userService.login(requestUser);
		return Response.status(200).entity(jwtResponse).build();
		
	}


}
