package com.harsha.cloudcomputing.courseservice.resources;

import java.util.ArrayList;
import java.util.Date;
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

import com.harsha.cloudcomputing.courseservice.datamodel.Course;
import com.harsha.cloudcomputing.courseservice.datamodel.Student;
import com.harsha.cloudcomputing.courseservice.service.CoursesService;
import com.harsha.cloudcomputing.courseservice.service.StudentsService;

/**
 * StudentsResource
 */
@Path("/students")
public class StudentsResource {

    private StudentsService studentsService = new StudentsService();
    private CoursesService coursesService = new CoursesService();

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
    public Student getStudent(@PathParam("studentId") Long id) {
        return studentsService.getStudent(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Student createStudent(Student student) {
        return studentsService.addStudent(student.getFirstName(), student.getLastName(), student.getImage(),
                student.getProgram(), new Date());
    }

    @PUT
    @Path("/{studentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Student updateStudent(@PathParam("studentId") Long id, Student student) {
        return studentsService.updateStudentInformation(id, student);
    }

    @DELETE
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student deleteStudent(@PathParam("studentId") Long id) {
        return studentsService.deleteStudent(id);
    }

    @GET
    @Path("/{studentId}/courses")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getEnrolledCourses(@PathParam("studentId") Long id) {
        Student student = studentsService.getStudent(id);
        return student != null ? student.getCoursesEnrolled() : new ArrayList<>();
    }

    @POST
    @Path("/{studentId}/courses/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student enrollStudentToCourse(@PathParam("studentId") Long studentId, @PathParam("courseId") Long courseId) {
        Student student = studentsService.getStudent(studentId);
        Course course = coursesService.getCourse(courseId);
        coursesService.enrollStudent(courseId, student);
        return studentsService.enrollStudentToCourse(studentId, course);
    }

    @DELETE
    @Path("/{studentId}/courses/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student disenrollStudentFromCourse(@PathParam("studentId") Long studentId,
            @PathParam("courseId") Long courseId) {
        Student student = studentsService.getStudent(studentId);
        Course course = coursesService.getCourse(courseId);
        coursesService.disenrollStudent(courseId, student);
        return studentsService.disenrollStudentFromCourse(studentId, course);
    }

}