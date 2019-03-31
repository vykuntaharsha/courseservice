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
import com.harsha.cloudcomputing.courseservice.datamodel.Professor;
import com.harsha.cloudcomputing.courseservice.service.ProfessorsService;
import org.apache.log4j.Logger;
import io.swagger.annotations.Api;

/**
 * ProfessorsResource
 */
@Api
@Path("/professors")
public class ProfessorsResource {
    ProfessorsService profService = new ProfessorsService();
    private static Logger log = Logger.getLogger(ProfessorsResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Professor> getProfessors(@QueryParam("program") String program,
            @QueryParam("joinedYear") Integer year) {
        log.info(
                "Get request received for professors with program: " + program + ", year: " + year);
        if (program != null && year != null) {
            return profService.getProfessorsWith(program, year);
        } else if (program != null && year == null) {
            return profService.getProfessorsOfProgram(program);
        } else if (program == null && year != null) {
            log.info("Get professors for year: " + year);
            return profService.getProfessorsJoinedInYear(year);
        }

        return profService.getAllProfessors();
    }

    // ... webapi/professor/1
    @GET
    @Path("/{professorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Professor getProfessor(@PathParam("professorId") String profId) {
        Professor prof = profService.getProfessor(profId);
        if (prof == null) {
            prof = profService.getProfessorWithProfessorId(profId);
        }
        return prof;
    }

    @DELETE
    @Path("/{professorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Professor deleteProfessor(@PathParam("professorId") String profId) {
        return profService.deleteProfessor(profId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Professor addProfessor(Professor professor) {
        return profService.addProfessor(professor);
    }

    @PUT
    @Path("/{professorId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Professor updateProfessor(@PathParam("professorId") String profId, Professor prof) {
        return profService.updateProfessorInformation(profId, prof);
    }
}
