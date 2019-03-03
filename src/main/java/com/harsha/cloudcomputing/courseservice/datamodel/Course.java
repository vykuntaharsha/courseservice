package com.harsha.cloudcomputing.courseservice.datamodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Course
 */
public class Course {

    private Long id;
    private String name;
    private String courseId;
    private Student teachingAssistant;
    private Professor professor;
    private long programId;
    private List<Student> enrolledStudents;

    public Course() {

    }

    /**
     * @param courseId          the courseId to set
     * @param name              the name to set
     * @param professor         the professor to set
     * @param teachingAssistant the teachingAssistant to set
     * @param program           the program to set
     */
    public Course(String courseId, String name, Professor professor, Student teachingAssistant, long programId) {
        this.courseId = courseId;
        this.name = name;
        this.teachingAssistant = teachingAssistant;
        this.professor = professor;
        this.programId = programId;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the professor
     */
    public Professor getProfessor() {
        return professor;
    }

    /**
     * @param professor the professor to set
     */
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    /**
     * @return the teachingAssistant
     */
    public Student getTeachingAssistant() {
        return teachingAssistant;
    }

    /**
     * @param teachingAssistant the teachingAssistant to set
     */
    public void setTeachingAssistant(Student teachingAssistant) {
        this.teachingAssistant = teachingAssistant;
    }

    /**
     * @return the enrolledStudents
     */
    public List<Student> getEnrolledStudents() {
        return this.getEnrolledStudents();
    }

    /**
     * @param enrolledStudents the enrolledStudents to set
     */
    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    /**
     * @return the roster
     */
    public List<Object> getRoster() {
        List<Object> roster = new ArrayList<>(enrolledStudents);
        roster.add(professor);
        roster.add(teachingAssistant);
        return roster;
    }

    /**
     * @return the program
     */
    public long getProgramId() {
        return programId;
    }

    /**
     * @param program the program to set
     */
    public void setProgramId(long program) {
        this.programId = program;
    }

    /**
     * @return the courseId
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * @param courseId the courseId to set
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the details of Course
     */
    @Override
    public String toString() {
        return "courseId= " + getCourseId() + ", name= " + getName() + ", professor= " + getProfessor().getFirstName()
                + ", teachingAssistant= " + getTeachingAssistant().getFirstName();
    }
}