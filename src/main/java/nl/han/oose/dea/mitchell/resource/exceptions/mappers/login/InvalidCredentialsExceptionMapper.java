package nl.han.oose.dea.mitchell.resource.exceptions.mappers.login;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import nl.han.oose.dea.mitchell.resource.exceptions.ErrorMessage;
import nl.han.oose.dea.mitchell.service.exceptions.InvalidCredentialsException;

@Provider
public class InvalidCredentialsExceptionMapper implements ExceptionMapper<InvalidCredentialsException> {
    @Override
    public Response toResponse(InvalidCredentialsException e) {
        return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorMessage.INVALID_CREDENTIALS_MESSAGE.label).build();
    }
}
