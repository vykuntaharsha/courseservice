package com.harsha.cloudcomputing.courseservice.datamodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Student
 */
public class Student {
    private Long id;
    private String firstName;
    private String lastName;
    private String studentId;
    private String image;
    private String program;
    private List<Course> coursesEnrolled;
    private String joiningDate;

    public Student() {

    }

    /**
     * 
     * @param studentId       the studentId to set
     * @param firstName       the firstName to set
     * @param lastName        the lastName to set
     * @param image           the image to set
     * @param program         the program to set
     * @param coursesEnrolled the coursesEnrolled to set
     * @param joiningDate     the joiningDate to set
     */
    public Student(String studentId, String firstName, String lastName, String image, String program,
            List<Course> coursesEnrolled, String joiningDate) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.studentId = studentId;
        this.image = image;
        this.program = program;
        this.coursesEnrolled = coursesEnrolled;
        this.joiningDate = joiningDate;
    }

    /**
     * @param studentId the studentId to set
     * @param firstName the firstName to set
     * @param lastName  the lastName to set
     * @param image     the image to set
     * @param program   the program to set
     */
    public Student(String studentId, String firstName, String lastName, String image, String program) {
        this(studentId, firstName, lastName, image, program, new ArrayList<Course>(), new Date().toString());
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the coursesEnrolled
     */
    public List<Course> getCoursesEnrolled() {
        return coursesEnrolled;
    }

    /**
     * @param coursesEnrolled the coursesEnrolled to set
     */
    public void setCoursesEnrolled(List<Course> coursesEnrolled) {
        this.coursesEnrolled = coursesEnrolled;
    }

    /**
     * @return the program
     */
    public String getProgram() {
        return program;
    }

    /**
     * @param program the program to set
     */
    public void setProgram(String program) {
        this.program = program;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the studentId
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * @param studentId the studentId to set
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the joiningDate
     */
    public String getJoiningDate() {
        return joiningDate;
    }

    /**
     * @param joiningDate the joiningDate to set
     */
    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    /**
     * @return the content of Student
     */
    @Override
    public String toString() {
        return "studentId= " + getStudentId() + ", firstName= " + getFirstName() + ", program= " + getProgram()
                + ", joiningDate= " + getJoiningDate();
    }
}