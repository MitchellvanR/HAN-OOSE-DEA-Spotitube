package nl.han.oose.dea.mitchell.resource.exceptions.mappers.data;

import nl.han.oose.dea.mitchell.datasource.exceptions.SQLQueryException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import nl.han.oose.dea.mitchell.resource.exceptions.ErrorMessage;

public class SQLQueryExceptionMapper implements ExceptionMapper<SQLQueryException> {
    @Override
    public Response toResponse(SQLQueryException e) {
        return Response.status(Response.Status.NO_CONTENT).entity(ErrorMessage.SQL_QUERY_EXCEPTION_MESSAGE.label).build();
    }
}
