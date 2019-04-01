package com.harsha.cloudcomputing.courseservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.harsha.cloudcomputing.courseservice.datamodel.Course;
import com.harsha.cloudcomputing.courseservice.datamodel.DynamoDBConnector;

/**
 * CoursesService
 */
public class CoursesService {

    private DynamoDBMapper mapper;

    public CoursesService() {
        DynamoDBConnector.init();
        mapper = new DynamoDBMapper(new DynamoDBConnector().getClient());
    }

    public Course addCourse(Course course) {
        String courseId = "course-" + UUID.randomUUID();
        course.setCourseId(courseId);
        mapper.save(course);
        return course;
    }

    /**
     * 
     * @param name                to set for the course
     * @param board               to set for the course
     * @param professorId         to set for the course
     * @param teachingAssistantId to set for the course
     * @return the created course
     */
    public Course addCourse(String name, String boardId, String professorId,
            String teachingAssistantId, String program) {
        Course course = new Course(null, name, boardId, program, professorId, teachingAssistantId);
        return addCourse(course);
    }

    /**
     * @return all available courses
     */
    public List<Course> getCourses() {
        PaginatedList<Course> courses = mapper.scan(Course.class, new DynamoDBScanExpression());
        courses.loadAllResults();
        return courses;
    }

    /**
     * 
     * @param id to retrieve course
     * @return the course object
     */
    public Course getCourse(String id) {
        Course course = mapper.load(Course.class, id);
        return course;
    }

    /**
     * 
     * @param id     to update the coressponding course
     * @param course information to update
     * @return the updated course object
     */
    public Course updateCourse(String id, Course course) {
        Course courseToUpdate = getCourse(id);
        if (courseToUpdate == null) {
            return null;
        }
        course.setCourseId(courseToUpdate.getCourseId());
        course.setId(courseToUpdate.getId());
        mapper.save(course);
        return course;
    }

    /**
     * 
     * @param id to delete course
     * @return the deleted course object
     */
    public Course deleteCourse(String id) {
        Course courseToDelete = getCourse(id);
        if (courseToDelete == null) {
            return null;
        }
        mapper.delete(courseToDelete);
        return courseToDelete;
    }

    /**
     * 
     * @param id        of course to enroll student
     * @param studentId studentId to enroll
     * @return updated course object
     */
    public Course enrollStudent(String id, String studentId) {
        Course courseToEnroll = getCourse(id);
        if (courseToEnroll == null) {
            return null;
        }
        courseToEnroll.getRoster().add(studentId);
        mapper.save(courseToEnroll);
        return courseToEnroll;
    }

    /**
     * @param id        of course to disenroll student
     * @param studentId studentId to disenroll
     * @return updated course object
     */
    public Course disenrollStudent(String id, String studentId) {
        Course courseToDisenroll = getCourse(id);
        if (courseToDisenroll == null) {
            return null;
        }
        courseToDisenroll.getRoster().removeIf(sId -> sId.equals(studentId));
        mapper.save(courseToDisenroll);
        return courseToDisenroll;
    }


    public List<Course> getCoursesOfProgram(String program) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":program", new AttributeValue().withS(program));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("program = :program").withExpressionAttributeValues(eav);

        PaginatedList<Course> courseList = mapper.scan(Course.class, scanExpression);
        courseList.loadAllResults();
        return courseList;
    }

    public List<Course> getCoursesOfProfessor(String professorId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":professorId", new AttributeValue().withS(professorId));

        DynamoDBScanExpression scanExpression =
                new DynamoDBScanExpression().withFilterExpression("professorId = :professorId")
                        .withExpressionAttributeValues(eav);

        PaginatedList<Course> courseList = mapper.scan(Course.class, scanExpression);
        courseList.loadAllResults();
        return courseList;
    }

}
