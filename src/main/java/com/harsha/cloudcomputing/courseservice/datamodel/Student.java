package com.harsha.cloudcomputing.courseservice.datamodel;

import java.util.ArrayList;
import java.util.List;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Student
 */
@DynamoDBTable(tableName = "students")
public class Student {

    private String id;
    private String firstName;
    private String lastName;
    private String studentId;
    private String image;
    private String program;
    private String joiningDate;
    @JsonIgnore
    private List<String> coursesEnrolled = new ArrayList<>();

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
    public Student(String studentId, String firstName, String lastName, String image,
            String program, List<String> coursesEnrolled, String joiningDate) {
        this(studentId, firstName, lastName, image, program);
        this.coursesEnrolled = coursesEnrolled;
        this.joiningDate = joiningDate;
    }

    public Student(String studentId, String firstName, String lastName, String image,
            String program) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.studentId = studentId;
        this.image = image;
        this.program = program;
    }

    /**
     * @return the id
     */
    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    @DynamoDBAttribute(attributeName = "firstName")
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
     * @return the lastName
     */
    @DynamoDBAttribute(attributeName = "lastName")
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
     * @return the studentId
     */
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "studentId-index", attributeName = "studentId")
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
     * @return the image
     */
    @DynamoDBAttribute(attributeName = "image")
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
     * @return the program
     */
    @DynamoDBAttribute(attributeName = "program")
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
     * @return the joiningDate
     */
    @DynamoDBAttribute(attributeName = "joiningDate")
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
     * @return the coursesEnrolled
     */
    @DynamoDBAttribute(attributeName = "coursesEnrolled")
    @JsonProperty
    public List<String> getCoursesEnrolled() {
        return coursesEnrolled;
    }

    /**
     * @param coursesEnrolled the coursesEnrolled to set
     */
    @JsonIgnore
    public void setCoursesEnrolled(List<String> coursesEnrolled) {
        this.coursesEnrolled = coursesEnrolled;
    }

    @DynamoDBIgnore
    @Override
    public String toString() {
        return "studentId= " + getStudentId() + ", firstName= " + getFirstName() + ", program= "
                + getProgram() + ", joiningDate= " + getJoiningDate();
    }
}
