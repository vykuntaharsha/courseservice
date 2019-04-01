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
import com.harsha.cloudcomputing.courseservice.datamodel.Student;
import com.harsha.cloudcomputing.courseservice.service.StudentsService;
import io.swagger.annotations.Api;

/**
 * StudentsResource
 */
@Api
@Path("/students")
public class StudentsResource {

    private StudentsService studentsService = new StudentsService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getStudents(@QueryParam("program") String program) {
        if (program != null) {
            return studentsService.getStudentsByProgram(program);
        }
        return studentsService.getStudents();
    }

    @GET
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudent(@PathParam("studentId") String id) {
        Student student = studentsService.getStudent(id);
        if (student != null) {
            return student;
        }
        return studentsService.getStudentWithStudentId(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Student createStudent(Student student) {
        return studentsService.createStudent(student);
    }

    @PUT
    @Path("/{studentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Student updateStudent(@PathParam("studentId") String id, Student student) {
        return studentsService.updateStudent(id, student);
    }

    @DELETE
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student deleteStudent(@PathParam("studentId") String id) {
        return studentsService.deleteStudent(id);
    }

}
