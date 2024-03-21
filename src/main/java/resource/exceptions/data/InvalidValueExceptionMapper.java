package resource.exceptions.data;

import datasource.exceptions.InvalidValueException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import resource.exceptions.ErrorMessage;

public class InvalidValueExceptionMapper implements ExceptionMapper<InvalidValueException> {
    @Override
    public Response toResponse(InvalidValueException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorMessage.INVALID_VALUE_EXCEPTION_MESSAGE.label).build();
    }
}
