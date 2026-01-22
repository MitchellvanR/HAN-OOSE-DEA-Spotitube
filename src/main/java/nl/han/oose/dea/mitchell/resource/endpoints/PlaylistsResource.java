package nl.han.oose.dea.mitchell.resource.endpoints;

import nl.han.oose.dea.mitchell.domain.dto.playlists.Playlist;
import nl.han.oose.dea.mitchell.domain.dto.tracks.Track;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.mitchell.resource.util.RESTAuthenticator;
import nl.han.oose.dea.mitchell.service.business.PlaylistsService;
import nl.han.oose.dea.mitchell.service.business.TracksService;

@Path("/playlists")
public class PlaylistsResource {
    private RESTAuthenticator authenticator;
    private PlaylistsService playlistsService;
    private TracksService tracksService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token) {
        authenticator.validateUserLogin(token);
        return Response.ok().entity(playlistsService.getAllPlaylists(authenticator.getUserid())).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") String id) {
        authenticator.validateUserLogin(token);
        return Response.ok().entity(playlistsService.deletePlaylist(authenticator.getUserid(), id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, Playlist playlist) {
        authenticator.validateUserLogin(token);
        return Response.ok().entity(playlistsService.addPlaylist(authenticator.getUserid(), playlist)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(@QueryParam("token") String token, @PathParam("id") String id, Playlist playlist) {
        authenticator.validateUserLogin(token);
        return Response.ok().entity(playlistsService.editPlaylist(authenticator.getUserid(), id, playlist)).build();
    }

    @GET
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksFromPlaylist(@QueryParam("token") String token, @PathParam("id") String id) {
        authenticator.validateUserLogin(token);
        return Response.ok().entity(tracksService.getTracksFromPlaylist(id)).build();
    }

    @POST
    @Path("/{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("id") String id, Track track) {
        authenticator.validateUserLogin(token);
        return Response.ok().entity(tracksService.addTrackToPlaylist(id, track)).build();
    }

    @DELETE
    @Path("/{playlistId}/tracks/{trackId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackFromPlaylist(@QueryParam("token") String token, @PathParam("playlistId") String playlistId, @PathParam("trackId") String trackId) {
        authenticator.validateUserLogin(token);
        return Response.ok().entity(tracksService.deleteTrackFromPlaylist(playlistId, trackId)).build();
    }

    @Inject
    public void setAuthenticator(RESTAuthenticator restAuthenticator) {
        this.authenticator = restAuthenticator;
    }

    @Inject
    public void setPlaylistsService(PlaylistsService playlistsService) {
        this.playlistsService = playlistsService;
    }

    @Inject
    public void setTracksService(TracksService tracksService) { this.tracksService = tracksService; }
}
