package resource.exceptions.mappers.data;

import datasource.exceptions.PropertyLoadException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import resource.exceptions.ErrorMessage;

@Provider
public class PropertyLoadExceptionMapper implements ExceptionMapper<PropertyLoadException> {
    @Override
    public Response toResponse(PropertyLoadException e) {
        return Response.status(Response.Status.NOT_FOUND).entity(ErrorMessage.PROPERTY_LOAD_EXCEPTION_MESSAGE.label).build();
    }
}
