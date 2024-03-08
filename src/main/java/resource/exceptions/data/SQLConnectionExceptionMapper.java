package resource.exceptions.data;

import datasource.exceptions.SQLConnectionException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import resource.exceptions.ErrorMessage;

@Provider
public class SQLConnectionExceptionMapper implements ExceptionMapper<SQLConnectionException> {
    @Override
    public Response toResponse(SQLConnectionException e) {
        return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(ErrorMessage.SQL_CONNECTION_EXCEPTION_MESSAGE.label).build();
    }
}
