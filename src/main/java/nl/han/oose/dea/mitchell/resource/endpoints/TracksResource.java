package nl.han.oose.dea.mitchell.resource.endpoints;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.mitchell.resource.util.RESTAuthenticator;
import nl.han.oose.dea.mitchell.service.business.TracksService;

@Path("/tracks")
public class TracksResource {
    private RESTAuthenticator authenticator;
    private TracksService tracksService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAvailableTracks(@QueryParam("forPlaylist") int playlistId, @QueryParam("token") String token) {
        authenticator.validateUserLogin(token);
        return Response.ok().entity(tracksService.getAllAvailableTracks(playlistId)).build();
    }

    @Inject
    public void setAuthenticator(RESTAuthenticator restAuthenticator) {
        this.authenticator = restAuthenticator;
    }

    @Inject
    public void setTracksService(TracksService tracksService) {
        this.tracksService = tracksService;
    }
}
