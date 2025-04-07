package nl.han.oose.dea.mitchell.resource;

import nl.han.oose.dea.mitchell.domain.dto.playlists.Playlist;
import nl.han.oose.dea.mitchell.domain.dto.tracks.Track;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.mitchell.service.PlaylistsService;
import nl.han.oose.dea.mitchell.service.TracksService;

@Path("/playlists")
public class PlaylistsResource extends Resource {
    private PlaylistsService playlistsService;
    private TracksService tracksService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token) {
        validateUserLogin(token);
        return Response.ok().entity(playlistsService.getAllPlaylists(token)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") String id) {
        validateUserLogin(token);
        return Response.ok().entity(playlistsService.deletePlaylist(token, id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, Playlist playlist) {
        validateUserLogin(token);
        return Response.ok().entity(playlistsService.addPlaylist(token, playlist)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(@QueryParam("token") String token, @PathParam("id") String id, Playlist playlist) {
        validateUserLogin(token);
        return Response.ok().entity(playlistsService.editPlaylist(token, id, playlist)).build();
    }

    @GET
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksFromPlaylist(@QueryParam("token") String token, @PathParam("id") String id) {
        validateUserLogin(token);
        return Response.ok().entity(tracksService.getTracksFromPlaylist(id)).build();
    }

    @POST
    @Path("/{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("id") String id, Track track) {
        validateUserLogin(token);
        return Response.ok().entity(tracksService.addTrackToPlaylist(id, track)).build();
    }

    @DELETE
    @Path("/{playlistId}/tracks/{trackId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackFromPlaylist(@QueryParam("token") String token, @PathParam("playlistId") String playlistId, @PathParam("trackId") String trackId) {
        validateUserLogin(token);
        return Response.ok().entity(tracksService.deleteTrackFromPlaylist(playlistId, trackId)).build();
    }

    @Inject
    public void setPlaylistsService(PlaylistsService playlistsService) {
        this.playlistsService = playlistsService;
    }

    @Inject
    public void setTracksService(TracksService tracksService) { this.tracksService = tracksService; }
}
