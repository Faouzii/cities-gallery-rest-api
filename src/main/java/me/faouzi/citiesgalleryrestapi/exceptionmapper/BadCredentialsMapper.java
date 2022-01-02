package me.faouzi.citiesgalleryrestapi.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;



@Provider
public class BadCredentialsMapper implements ExceptionMapper<BadCredentialsException>{

	
	private static final Logger logger = LoggerFactory.getLogger(BadCredentialsMapper.class);

	@Override
	public Response toResponse(BadCredentialsException e) {
		logger.error(e.getMessage());
		return Response.status(401).build();
	}
}
