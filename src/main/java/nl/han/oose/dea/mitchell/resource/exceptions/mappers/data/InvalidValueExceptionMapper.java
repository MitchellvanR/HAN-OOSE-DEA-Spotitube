package nl.han.oose.dea.mitchell.resource.exceptions.mappers.data;

import nl.han.oose.dea.mitchell.datasource.exceptions.InvalidValueException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import nl.han.oose.dea.mitchell.resource.exceptions.ErrorMessage;

public class InvalidValueExceptionMapper implements ExceptionMapper<InvalidValueException> {
    @Override
    public Response toResponse(InvalidValueException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorMessage.INVALID_VALUE_EXCEPTION_MESSAGE.label).build();
    }
}
