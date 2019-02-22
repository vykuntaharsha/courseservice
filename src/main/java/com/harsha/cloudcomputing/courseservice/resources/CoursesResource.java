package com.harsha.cloudcomputing.courseservice.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.harsha.cloudcomputing.courseservice.datamodel.Lecture;
import com.harsha.cloudcomputing.courseservice.datamodel.Note;
import com.harsha.cloudcomputing.courseservice.datamodel.Student;
import com.harsha.cloudcomputing.courseservice.service.CoursesService;
import com.harsha.cloudcomputing.courseservice.service.LecturesService;
import com.harsha.cloudcomputing.courseservice.service.NotesService;
import com.harsha.cloudcomputing.courseservice.service.StudentsService;

/**
 * CoursesResource
 */
@Path("/courses")
public class CoursesResource {

    private CoursesService coursesService = new CoursesService();
    private StudentsService studentsService = new StudentsService();
    private LecturesService lecturesService = new LecturesService();
    private NotesService notesService = new NotesService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCourses(@QueryParam("professorId") Long professorId) {
        if (professorId != null) {
            return coursesService.getCoursesOfProfessor(professorId);
        }
        return coursesService.getCourses();
    }

    @GET
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Course getCourseDetails(@PathParam("courseId") Long id) {
        return coursesService.getCourse(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Course createCourse(Course course) {
        return coursesService.addCourse(course.getName(), course.getBoard(), course.getProfessor(),
                course.getTeachingAssistant());
    }

    @PUT
    @Path("/{courseId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Course updateCourse(@PathParam("courseId") Long id, Course course) {
        return coursesService.updateCourseInformation(id, course);
    }

    @DELETE
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Course deleteCourse(@PathParam("courseId") Long id) {
        return coursesService.deleteCourse(id);
    }

    @GET
    @Path("/{courseId}/students")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getEnrolledStudents(@PathParam("courseId") Long id) {
        Course course = coursesService.getCourse(id);
        return course != null ? course.getEnrolledStudents() : new ArrayList<>();
    }

    @POST
    @Path("/{courseId}/students/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Course enrollStudentToCourse(@PathParam("courseId") Long courseId, @PathParam("studentId") Long studentId) {
        Course course = coursesService.getCourse(courseId);
        Student student = studentsService.getStudent(studentId);
        studentsService.enrollStudentToCourse(studentId, course);
        return coursesService.enrollStudent(courseId, student);

    }

    @DELETE
    @Path("/{courseId}/students/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Course disenrollStudentToCourse(@PathParam("courseId") Long courseId,
            @PathParam("studentId") Long studentId) {
        Course course = coursesService.getCourse(courseId);
        Student student = studentsService.getStudent(studentId);
        studentsService.disenrollStudentFromCourse(studentId, course);
        return coursesService.disenrollStudent(courseId, student);
    }

    @GET
    @Path("/{courseId}/lectures")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Lecture> getLecturesOfCourse(@PathParam("courseId") Long id) {
        return lecturesService.getLecturesOfCourse(id);
    }

    @POST
    @Path("/{courseId}/lectures")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> addLectureToCourse(@PathParam("courseId") Long id, Lecture lecture) {
        Course course = coursesService.getCourse(id);
        lecture.setCourse(course);
        lecturesService.addLecture(lecture);
        List<Lecture> lectures = lecturesService.getLecturesOfCourse(id);

        Map<String, Object> resultObj = new HashMap<>();
        resultObj.put("meta", course);
        resultObj.put("lectures", lectures);

        return resultObj;
    }

    @PUT
    @Path("/{courseId}/lectures/{lectureId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Lecture updateLecture(@PathParam("courseId") Long courseId, @PathParam("lectureId") Long lectureId,
            Lecture lecture) {
        Lecture lectureToUpdate = lecturesService.getLecture(lectureId);
        if (lectureToUpdate.getCourse().getId() != courseId) {
            return null;
        }

        lecture.setCourse(lectureToUpdate.getCourse());
        return lecturesService.updateLectureInformation(lectureId, lecture);
    }

    @DELETE
    @Path("/{courseId}/lectures/{lectureId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Lecture deleteLecture(@PathParam("courseId") Long courseId, @PathParam("lectureId") Long lectureId) {

        Lecture lectureToDelete = lecturesService.getLecture(lectureId);
        if (lectureToDelete.getCourse().getId() != courseId) {
            return null;
        }
        return lecturesService.deleteLecture(lectureId);
    }

    @GET
    @Path("/{courseId}/lectures/{lectureId}/notes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Note> getNotesOfLecture(@PathParam("courseId") Long courseId, @PathParam("lectureId") Long lectureId) {
        Lecture lecture = lecturesService.getLecture(lectureId);
        if (lecture.getCourse().getId() != courseId) {
            return new ArrayList<>();
        }

        return notesService.getNotesOfLecture(lectureId);
    }

    @POST
    @Path("/{courseId}/lectures/{lectureId}/notes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> addNoteToLecture(@PathParam("courseId") Long courseId,
            @PathParam("lectureId") Long lectureId, Note note) {

        Lecture lecture = lecturesService.getLecture(lectureId);
        if (lecture.getCourse().getId() != courseId) {
            return null;
        }
        note.setLecture(lecture);
        notesService.addNote(note);

        Map<String, Object> resultObj = new HashMap<>();
        resultObj.put("meta", lecture);
        resultObj.put("notes", notesService.getNotesOfLecture(lectureId));

        return resultObj;
    }

    @PUT
    @Path("/{courseId}/lectures/{lectureId}/notes/{noteId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Note updateNoteOfLecture(@PathParam("courseId") Long courseId, @PathParam("lectureId") Long lectureId,
            @PathParam("noteId") Long noteId, Note note) {
        Lecture lecture = lecturesService.getLecture(lectureId);
        Note noteToUpdate = notesService.getNote(noteId);
        if (lecture.getCourse().getId() != courseId || noteToUpdate.getLecture().getId() != lectureId) {
            return null;
        }

        note.setLecture(noteToUpdate.getLecture());
        return notesService.updateNoteDetails(noteId, note);
    }

    @DELETE
    @Path("/{courseId}/lectures/{lectureId}/notes/{noteId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Note deleteNoteOfLecture(@PathParam("courseId") Long courseId, @PathParam("lectureId") Long lectureId,
            @PathParam("noteId") Long noteId) {
        Lecture lecture = lecturesService.getLecture(lectureId);
        Note noteToUpdate = notesService.getNote(noteId);
        if (lecture.getCourse().getId() != courseId || noteToUpdate.getLecture().getId() != lectureId) {
            return null;
        }
        return notesService.deleteNote(noteId);
    }

}