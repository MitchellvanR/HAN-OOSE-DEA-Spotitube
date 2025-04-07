package nl.han.oose.dea.mitchell.resource.exceptions.mappers.data;

import nl.han.oose.dea.mitchell.datasource.exceptions.SQLDisconnectException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import nl.han.oose.dea.mitchell.resource.exceptions.ErrorMessage;

public class SQLDisconnectExceptionMapper implements ExceptionMapper<SQLDisconnectException> {
    @Override
    public Response toResponse(SQLDisconnectException e) {
        return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(ErrorMessage.SQL_CONNECTION_EXCEPTION_MESSAGE.label).build();
    }
}
