package me.faouzi.citiesgalleryrestapi.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CityNotFoundException  extends Exception implements ExceptionMapper<CityNotFoundException> {
	
	private static final long serialVersionUID = 1L;
	 
    public CityNotFoundException() {
        super("City not found !");
    }
 
    public CityNotFoundException(String string) {
        super(string);
    }
 
	@Override
	public Response toResponse(CityNotFoundException exception) {
		return Response.status(401).entity(exception.getMessage())
                .type("text/plain").build();
	}

}
