package com.harsha.cloudcomputing.courseservice.datamodel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Student
 */
public class Student {
    @JsonIgnore
    private Long id;
    private String firstName;
    private String lastName;

    @JsonIgnore
    private String studentId;
    private String image;
    private String program;

    @JsonIgnore
    private List<Course> coursesEnrolled = new ArrayList<>();

    @JsonIgnore
    private LocalDate joiningDate;

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
            List<Course> coursesEnrolled, LocalDate joiningDate) {
        this(studentId, firstName, lastName, image, program);
        this.coursesEnrolled = coursesEnrolled;
        this.joiningDate = joiningDate;
    }

    public Student(String studentId, String firstName, String lastName, String image, String program) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.studentId = studentId;
        this.image = image;
        this.program = program;
    }

    /**
     * @return the id
     */
    @JsonProperty
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the coursesEnrolled
     */
    @JsonProperty
    public List<Course> getCoursesEnrolled() {
        return coursesEnrolled;
    }

    /**
     * @param coursesEnrolled the coursesEnrolled to set
     */
    @JsonIgnore
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
    @JsonProperty
    public String getStudentId() {
        return studentId;
    }

    /**
     * @param studentId the studentId to set
     */
    @JsonIgnore
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
     * @return the joiningDate
     */
    @JsonProperty
    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    /**
     * @param joiningDate the joiningDate to set
     */
    @JsonIgnore
    public void setJoiningDate(LocalDate joiningDate) {
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