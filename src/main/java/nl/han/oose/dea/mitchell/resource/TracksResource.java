package nl.han.oose.dea.mitchell.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.mitchell.resource.exceptions.UserNotLoggedInException;
import nl.han.oose.dea.mitchell.service.TracksService;

@Path("/tracks")
public class TracksResource extends Resource {

    private TracksService tracksService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracks(@QueryParam("token") String token) {
        validateUserLogin(token);
        return Response.ok().entity(tracksService.getAllTracks()).build();
    }

    @Inject
    public void setTracksService(TracksService tracksService) {
        this.tracksService = tracksService;
    }
}
