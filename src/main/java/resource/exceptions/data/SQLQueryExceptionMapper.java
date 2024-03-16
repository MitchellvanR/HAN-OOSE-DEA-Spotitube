package resource.exceptions.data;

import datasource.exceptions.SQLQueryException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import resource.exceptions.ErrorMessage;

public class SQLQueryExceptionMapper implements ExceptionMapper<SQLQueryException> {
    @Override
    public Response toResponse(SQLQueryException e) {
        return Response.status(Response.Status.NO_CONTENT).entity(ErrorMessage.SQL_QUERY_EXCEPTION_MESSAGE.label).build();
    }
}
