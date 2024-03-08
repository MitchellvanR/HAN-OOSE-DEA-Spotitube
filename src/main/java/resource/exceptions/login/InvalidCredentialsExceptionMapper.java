package resource.exceptions.login;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import resource.exceptions.ErrorMessage;
import service.exceptions.InvalidCredentialsException;

@Provider
public class InvalidCredentialsExceptionMapper implements ExceptionMapper<InvalidCredentialsException> {
    @Override
    public Response toResponse(InvalidCredentialsException e) {
        return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorMessage.INVALID_CREDENTIALS_MESSAGE.label).build();
    }
}
