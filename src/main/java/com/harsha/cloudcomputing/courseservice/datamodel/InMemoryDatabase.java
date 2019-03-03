package com.harsha.cloudcomputing.courseservice.datamodel;

import java.util.HashMap;

/**
 * InMemoryDatabase
 */
public class InMemoryDatabase {
    private static HashMap<Long, Professor> professorDB = new HashMap<>();
    private static HashMap<Long, Student> studentDB = new HashMap<>();
    private static HashMap<Long, Course> courseDB = new HashMap<>();
    private static HashMap<Long, Lecture> lectureDB = new HashMap<>();
    private static HashMap<Long, Note> noteDB = new HashMap<>();
    private static HashMap<Long, Program> programDB = new HashMap<>();

    /**
     * @return the professorDB
     */
    public static HashMap<Long, Professor> getProfessorDB() {
        return professorDB;
    }

    /**
     * @return the programDB
     */
    public static HashMap<Long, Program> getProgramDB() {
        return programDB;
    }

    /**
     * @return the noteDB
     */
    public static HashMap<Long, Note> getNoteDB() {
        return noteDB;
    }

    /**
     * @return the lectureDB
     */
    public static HashMap<Long, Lecture> getLectureDB() {
        return lectureDB;
    }

    /**
     * @return the studentDB
     */
    public static HashMap<Long, Student> getStudentDB() {
        return studentDB;
    }

    /**
     * @return the courseDB
     */
    public static HashMap<Long, Course> getCourseDB() {
        return courseDB;
    }

}