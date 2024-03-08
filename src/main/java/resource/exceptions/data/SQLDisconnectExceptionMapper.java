package resource.exceptions.data;

import datasource.exceptions.SQLDisconnectException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import resource.exceptions.ErrorMessage;

public class SQLDisconnectExceptionMapper implements ExceptionMapper<SQLDisconnectException> {
    @Override
    public Response toResponse(SQLDisconnectException e) {
        return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(ErrorMessage.SQL_CONNECTION_EXCEPTION_MESSAGE.label).build();
    }
}
