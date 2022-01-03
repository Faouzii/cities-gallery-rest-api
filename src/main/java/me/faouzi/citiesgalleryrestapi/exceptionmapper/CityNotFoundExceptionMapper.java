package me.faouzi.citiesgalleryrestapi.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CityNotFoundExceptionMapper  extends Exception implements ExceptionMapper<CityNotFoundExceptionMapper> {
	
	private static final long serialVersionUID = 1L;
	 
    public CityNotFoundExceptionMapper() {
        super("City not found !");
    }
 
    public CityNotFoundExceptionMapper(String string) {
        super(string);
    }
 
	@Override
	public Response toResponse(CityNotFoundExceptionMapper exception) {
		return Response.status(404).entity(exception.getMessage())
                .type("text/plain").build();
	}

}
