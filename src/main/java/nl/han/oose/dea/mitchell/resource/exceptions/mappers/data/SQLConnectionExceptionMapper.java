package nl.han.oose.dea.mitchell.resource.exceptions.mappers.data;

import nl.han.oose.dea.mitchell.datasource.exceptions.SQLConnectionException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import nl.han.oose.dea.mitchell.resource.exceptions.ErrorMessage;

@Provider
public class SQLConnectionExceptionMapper implements ExceptionMapper<SQLConnectionException> {
    @Override
    public Response toResponse(SQLConnectionException e) {
        return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(ErrorMessage.SQL_CONNECTION_EXCEPTION_MESSAGE.label).build();
    }
}
