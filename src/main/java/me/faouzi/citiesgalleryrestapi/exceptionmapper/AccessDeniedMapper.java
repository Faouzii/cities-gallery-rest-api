package me.faouzi.citiesgalleryrestapi.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Provider
public class AccessDeniedMapper implements ExceptionMapper<AccessDeniedException> {
	
	private static final Logger logger = LoggerFactory.getLogger(AccessDeniedMapper.class);

	@Override
	public Response toResponse(AccessDeniedException e) {
		logger.error("Unauthorized error: {}", e.getMessage());
		return Response.status(401).build();
	}
}
