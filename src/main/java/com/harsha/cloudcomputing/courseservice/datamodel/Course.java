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
    private String board;
    private List<Object> roster;
    private List<Student> enrolledStudents;
    private Student teachingAssistant;
    private Professor professor;

    public Course() {

    }

    /**
     * @param courseId          the courseId to set
     * @param name              the name to set
     * @param board             the board to set
     * @param professor         the professor to set
     * @param teachingAssistant the teachingAssistant to set
     * @param roster            the roster to set
     * @param enrolledStudents  the enrolledStudents to set
     */
    public Course(String courseId, String name, String board, Professor professor, Student teachingAssistant,
            List<Object> roster, List<Student> enrolledStudents) {
        this.courseId = courseId;
        this.name = name;
        this.board = board;
        this.roster = roster;
        this.enrolledStudents = enrolledStudents;
        this.teachingAssistant = teachingAssistant;
        this.professor = professor;
    }

    /**
     * @param courseId          the courseId to set
     * @param name              the name to set
     * @param board             the board to set
     * @param professor         the professor to set
     * @param teachingAssistant the teachingAssistant to set
     */
    public Course(String courseId, String name, String board, Professor professor, Student teachingAssistant) {
        this(courseId, name, board, professor, teachingAssistant, new ArrayList<>(), new ArrayList<>());
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
        return enrolledStudents;
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
        return roster;
    }

    /**
     * @param roster the roster to set
     */
    public void setRoster(List<Object> roster) {
        this.roster = roster;
    }

    /**
     * @return the board
     */
    public String getBoard() {
        return board;
    }

    /**
     * @param board the board to set
     */
    public void setBoard(String board) {
        this.board = board;
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