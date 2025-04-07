package nl.han.oose.dea.mitchell.resource.exceptions.mappers.data;

import nl.han.oose.dea.mitchell.datasource.exceptions.PropertyLoadException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import nl.han.oose.dea.mitchell.resource.exceptions.ErrorMessage;

@Provider
public class PropertyLoadExceptionMapper implements ExceptionMapper<PropertyLoadException> {
    @Override
    public Response toResponse(PropertyLoadException e) {
        return Response.status(Response.Status.NOT_FOUND).entity(ErrorMessage.PROPERTY_LOAD_EXCEPTION_MESSAGE.label).build();
    }
}
