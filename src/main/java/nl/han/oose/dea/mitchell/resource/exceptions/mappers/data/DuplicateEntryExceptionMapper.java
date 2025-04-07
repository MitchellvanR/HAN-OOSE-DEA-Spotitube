package nl.han.oose.dea.mitchell.resource.exceptions.mappers.data;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import nl.han.oose.dea.mitchell.datasource.exceptions.DuplicateEntryException;
import nl.han.oose.dea.mitchell.resource.exceptions.ErrorMessage;

@Provider
public class DuplicateEntryExceptionMapper implements ExceptionMapper<DuplicateEntryException> {
    @Override
    public Response toResponse(DuplicateEntryException e) {
        return Response.status(Response.Status.CONFLICT).entity(ErrorMessage.DUPLICATE_ENTRY_MESSAGE.label).build();
    }
}
