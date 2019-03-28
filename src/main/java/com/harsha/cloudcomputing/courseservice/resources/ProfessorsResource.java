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
import com.harsha.cloudcomputing.courseservice.datamodel.Professor;
import com.harsha.cloudcomputing.courseservice.datamodel.Program;
import com.harsha.cloudcomputing.courseservice.service.ProfessorsService;
import com.harsha.cloudcomputing.courseservice.service.ProgramsService;
import io.swagger.annotations.Api;

/**
 * ProfessorsResource
 */
@Api
@Path("/professors")
public class ProfessorsResource {
    ProfessorsService profService = new ProfessorsService();
    ProgramsService programService = new ProgramsService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Professor> getProfessors(@QueryParam("program") String program,
            @QueryParam("joinedYear") Integer year, @QueryParam("limit") Integer limit) {

        return profService.getAllProfessors();
    }

    // ... webapi/professor/1
    @GET
    @Path("/{professorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Professor getProfessor(@PathParam("professorId") String profId) {
        System.out.println("Professor Resource: Looking for: " + profId);
        return profService.getProfessor(profId);
    }

    @DELETE
    @Path("/{professorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Professor deleteProfessor(@PathParam("professorId") long profId) {
        return profService.deleteProfessor(profId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProfessor(Professor professor) {
        String programName = professor.getProgram();
        Program program = programService.findByName(programName);
        if (program == null) {
            return Response.status(400).entity("No such program available").build();
        }
        professor = profService.addProfessor(professor);

        return Response.status(201).entity(professor).build();
    }

    @PUT
    @Path("/{professorId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Professor updateProfessor(@PathParam("professorId") long profId, Professor prof) {
        return profService.updateProfessorInformation(profId, prof);
    }
}
