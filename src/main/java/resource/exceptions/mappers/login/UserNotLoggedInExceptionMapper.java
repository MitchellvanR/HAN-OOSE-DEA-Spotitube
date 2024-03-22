package resource.exceptions.mappers.login;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import resource.exceptions.ErrorMessage;
import resource.exceptions.UserNotLoggedInException;

public class UserNotLoggedInExceptionMapper implements ExceptionMapper<UserNotLoggedInException> {
    @Override
    public Response toResponse(UserNotLoggedInException e) {
        return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorMessage.USER_NOT_LOGGED_IN_EXCEPTION_MESSAGE.label).build();
    }
}
