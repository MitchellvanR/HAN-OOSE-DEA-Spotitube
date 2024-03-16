package resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.PlaylistsService;

@Path("/playlists")
public class PlaylistsResource {
    private PlaylistsService playlistsService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token) {
        return Response.ok().entity(playlistsService.getAllPlaylists(token)).build();
    }

    @Inject
    public void setPlaylistsService(PlaylistsService playlistsService) {
        this.playlistsService = playlistsService;
    }
}
