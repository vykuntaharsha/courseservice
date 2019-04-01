package com.harsha.cloudcomputing.courseservice.resources;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.harsha.cloudcomputing.courseservice.datamodel.Announcement;
import com.harsha.cloudcomputing.courseservice.service.AnnouncementService;
import com.harsha.cloudcomputing.courseservice.util.ApplicationConstants;
import com.harsha.cloudcomputing.courseservice.util.ExceedsMaxCharactersException;
import io.swagger.annotations.Api;

/**
 * AnnouncementsResource
 */
@Api
@Path("/announcements")
public class AnnouncementsResource {

    private AnnouncementService announcementService = new AnnouncementService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Announcement> getAnnouncements(@QueryParam("boardId") String boardId) {
        if (boardId != null) {
            return announcementService.getAnnouncementsOfBoard(boardId);
        }
        return announcementService.getAnnouncements();
    }

    @GET
    @Path("/{announcementId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Announcement getAnnouncement(@PathParam("announcementId") String id) {
        return announcementService.getAnnouncement(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAnnouncement(Announcement announcement) {
        try {
            announcement = announcementService.createAnnouncement(announcement);
            return Response.status(201).entity(announcement).build();
        } catch (ExceedsMaxCharactersException e) {
            return Response.status(400).entity("Maximum allowed characters for an announcement is "
                    + ApplicationConstants.ANNOUNCEMENT_CHAR_LIMIT).build();
        }
    }

    @PUT
    @Path("/{announcementId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateResponse(@PathParam("announcementId") String id,
            Announcement announcement) {
        try {
            announcement = announcementService.updateAnnouncement(id, announcement);
            return Response.status(201).entity(announcement).build();
        } catch (ExceedsMaxCharactersException e) {
            return Response.status(400).entity("Maximum allowed characters for an announcement is "
                    + ApplicationConstants.ANNOUNCEMENT_CHAR_LIMIT).build();
        }
    }

    @DELETE
    @Path("/{announcementId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Announcement deleteAnnouncement(@PathParam("announcementId") String id) {
        return announcementService.deleteAnnouncement(id);
    }
}
