package com.harsha.cloudcomputing.courseservice.resources;

import java.util.ArrayList;
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
import com.harsha.cloudcomputing.courseservice.datamodel.Course;
import com.harsha.cloudcomputing.courseservice.datamodel.Student;
import com.harsha.cloudcomputing.courseservice.service.CoursesService;
import com.harsha.cloudcomputing.courseservice.service.StudentsService;
import io.swagger.annotations.Api;

/**
 * CoursesResource
 */
@Api
@Path("/courses")
public class CoursesResource {

    private CoursesService coursesService = new CoursesService();
    private StudentsService studentsService = new StudentsService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCourses(@QueryParam("professorId") String professorId,
            @QueryParam("program") String program) {
        if (professorId != null) {
            return coursesService.getCoursesOfProfessor(professorId);
        }
        if (program != null) {
            return coursesService.getCoursesOfProgram(program);
        }
        return coursesService.getCourses();
    }

    @GET
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Course getCourseDetails(@PathParam("courseId") String id) {
        return coursesService.getCourse(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Course createCourse(Course course) {
        return coursesService.addCourse(course);
    }

    @PUT
    @Path("/{courseId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Course updateCourse(@PathParam("courseId") String id, Course course) {
        return coursesService.updateCourse(id, course);
    }

    @DELETE
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Course deleteCourse(@PathParam("courseId") String id) {
        return coursesService.deleteCourse(id);
    }

    @GET
    @Path("/{courseId}/roster")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getRoster(@PathParam("courseId") String id) {
        Course course = coursesService.getCourse(id);
        return course != null ? course.getRoster() : new ArrayList<>();
    }

    @POST
    @Path("/{courseId}/roster/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response enrollStudentToCourse(@PathParam("courseId") String courseId,
            @PathParam("studentId") String studentId) {
        Student student = studentsService.getStudent(studentId);
        if (student == null) {
            return Response.status(400).entity("No student available with id = " + studentId)
                    .build();
        }

        Course course = coursesService.enrollStudent(courseId, studentId);
        if (course == null) {
            return Response.status(400).entity("No course available with id = " + courseId).build();
        }
        student.getCoursesEnrolled().add(course.getId());
        System.out.println(student.getCoursesEnrolled());
        studentsService.saveStudent(student);

        return Response.ok(course).build();

    }

    @DELETE
    @Path("/{courseId}/roster/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response disenrollStudentToCourse(@PathParam("courseId") String courseId,
            @PathParam("studentId") String studentId) {
        Student student = studentsService.getStudent(studentId);
        if (student == null) {
            return Response.status(400).entity("No student available with id = " + studentId)
                    .build();
        }
        Course course = coursesService.disenrollStudent(courseId, studentId);
        if (course == null) {
            return Response.status(400).entity("No course available with id = " + courseId).build();
        }
        student.getCoursesEnrolled().removeIf(id -> !id.equals(course.getId()));
        studentsService.updateStudent(studentId, student);
        return Response.ok(course).build();
    }

}
