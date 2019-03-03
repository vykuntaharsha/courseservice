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
import javax.ws.rs.core.MediaType;

import com.harsha.cloudcomputing.courseservice.datamodel.Course;
import com.harsha.cloudcomputing.courseservice.datamodel.Program;
import com.harsha.cloudcomputing.courseservice.service.CoursesService;
import com.harsha.cloudcomputing.courseservice.service.ProgramsService;

import io.swagger.annotations.Api;

/**
 * ProgramsResource
 */
@Api
@Path("/programs")
public class ProgramsResource {

    ProgramsService programsService = new ProgramsService();
    CoursesService coursesService = new CoursesService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Program> getPrograms() {
        return programsService.getAllPrograms();
    }

    @GET
    @Path("/{programId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Program getProgram(@PathParam("programId") long id) {
        return programsService.getProgram(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Program createProgram(Program program) {
        return programsService.addProgram(program);
    }

    @PUT
    @Path("/{programId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Program updateProgram(@PathParam("programId") long programId, Program program) {
        return programsService.updateProgram(program, programId);
    }

    @DELETE
    @Path("/{programId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Program deleteProgram(@PathParam("programId") long programId) {
        return programsService.deleteProgram(programId);
    }

    @GET
    @Path("/{programId}/courses")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCoursesOfProgram(@PathParam("programId") long programId) {
        return null;
    }

}