package resource;

import domain.dto.playlists.Playlist;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import resource.exceptions.UserNotLoggedInException;
import service.PlaylistsService;
import service.TracksService;

@Path("/playlists")
public class PlaylistsResource {
    private PlaylistsService playlistsService;
    private TracksService tracksService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token) {
        if (token == null) throw new UserNotLoggedInException();
        return Response.ok().entity(playlistsService.getAllPlaylists(token)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") String id) {
        if (token == null) throw new UserNotLoggedInException();
        return Response.ok().entity(playlistsService.deletePlaylist(token, id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, Playlist playlist) {
        if (token == null) throw new UserNotLoggedInException();
        return Response.ok().entity(playlistsService.addPlaylist(token, playlist)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(@QueryParam("token") String token, @PathParam("id") String id, Playlist playlist) {
        if (token == null) throw new UserNotLoggedInException();
        return Response.ok().entity(playlistsService.editPlaylist(token, id, playlist)).build();
    }

    @GET
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksFromPlaylist(@QueryParam("token") String token, @PathParam("id") String id) {
        if (token == null) throw new UserNotLoggedInException();
        return Response.ok().entity(tracksService.getTracksFromPlaylist(id)).build();
    }

    @Inject
    public void setPlaylistsService(PlaylistsService playlistsService) {
        this.playlistsService = playlistsService;
    }

    @Inject
    public void setTracksService(TracksService tracksService) { this.tracksService = tracksService; }
}
