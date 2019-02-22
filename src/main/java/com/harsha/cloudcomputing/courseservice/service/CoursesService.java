package com.harsha.cloudcomputing.courseservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.harsha.cloudcomputing.courseservice.datamodel.Course;
import com.harsha.cloudcomputing.courseservice.datamodel.InMemoryDatabase;
import com.harsha.cloudcomputing.courseservice.datamodel.Professor;
import com.harsha.cloudcomputing.courseservice.datamodel.Student;

import org.apache.commons.lang3.StringUtils;

/**
 * CoursesService
 */
public class CoursesService {

    private static HashMap<Long, Course> course_Map = InMemoryDatabase.getCourseDB();

    public CoursesService() {

    }

    /**
     * 
     * @param name              to set for the course
     * @param board             to set for the course
     * @param professor         to set for the course
     * @param teachingAssistant to set for the course
     * @return the created course
     */
    public Course addCourse(String name, String board, Professor professor, Student teachingAssistant) {
        long nextAvailableId = course_Map.size() + 1;
        String courseId = "C-" + StringUtils.leftPad(String.valueOf(nextAvailableId), 4, '0');
        Course course = new Course(courseId, name, board, professor, teachingAssistant);
        course.setId(nextAvailableId);
        course_Map.put(nextAvailableId, course);
        return course;
    }

    /**
     * @return all available courses
     */
    public List<Course> getCourses() {
        return course_Map.values().stream().filter(c -> c != null).collect(Collectors.toList());
    }

    public List<Course> getCoursesOfProfessor(Long professorId) {
        return course_Map.values().stream().filter(c -> c != null).filter(c -> c.getProfessor().getId() == professorId)
                .collect(Collectors.toList());
    }

    /**
     * 
     * @param id to retrieve course
     * @return the course object
     */
    public Course getCourse(Long id) {
        Course course = course_Map.get(id);
        System.out.println("Item retrieved:");
        System.out.println(course);
        return course;
    }

    /**
     * 
     * @param id to delete course
     * @return the deleted course object
     */
    public Course deleteCourse(Long id) {
        Course courseToDelete = course_Map.get(id);
        course_Map.put(id, null);
        return courseToDelete;
    }

    /**
     * 
     * @param id     to update the coressponding course
     * @param course information to update
     * @return the updated course object
     */
    public Course updateCourseInformation(Long id, Course course) {
        Course courseToUpdate = course_Map.get(id);
        if (courseToUpdate == null) {
            return null;
        }
        course.setCourseId(courseToUpdate.getCourseId());
        course_Map.put(id, course);
        return course;
    }

    /**
     * 
     * @param id      of course to enroll student
     * @param student object to enroll
     * @return updated course object
     */
    public Course enrollStudent(Long id, Student student) {
        Course courseToEnroll = course_Map.get(id);
        if (courseToEnroll == null) {
            return null;
        }
        courseToEnroll.getEnrolledStudents().add(student);
        return courseToEnroll;
    }

    /**
     * @param id      of course to disenroll student
     * @param student object to disenroll
     * @return updated course object
     */
    public Course disenrollStudent(Long id, Student student) {
        Course courseToDisenroll = course_Map.get(id);
        if (courseToDisenroll == null) {
            return null;
        }
        courseToDisenroll.getEnrolledStudents().removeIf(s -> s.getId() == student.getId());
        return courseToDisenroll;
    }

}