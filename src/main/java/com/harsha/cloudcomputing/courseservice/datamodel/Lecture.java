package com.harsha.cloudcomputing.courseservice.datamodel;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Lecture
 */
public class Lecture {

    @JsonIgnore
    private Long id;
    @JsonIgnore
    private String lectureId;
    private Course course;
    private String dateOfLecture;

    public Lecture() {

    }

    /**
     * @param lectureId the lectureId to set
     * @param course    the course to set
     * @param material  the material to set
     */
    public Lecture(String lectureId, Course course) {
        this(lectureId, course, new Date().toString());
    }

    /**
     * @param lectureId     the lectureId to set
     * @param course        the course to set
     * @param material      the material to set
     * @param dateOfLecture the dateOfLecture to set
     */
    public Lecture(String lectureId, Course course, String dateOfLecture) {
        this.lectureId = lectureId;
        this.course = course;
        this.dateOfLecture = dateOfLecture;
    }

    /**
     * @return the lectureId
     */
    @JsonProperty
    public String getLectureId() {
        return this.lectureId;
    }

    /**
     * @param lectureId the lectureId to set
     */
    @JsonIgnore
    public void setLectureId(String lectureId) {
        this.lectureId = lectureId;
    }

    /**
     * @return the course
     */
    public Course getCourse() {
        return this.course;
    }

    /**
     * @param course the course to set
     */
    public void setCourse(Course course) {
        this.course = course;
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
     * @return the dateOfLecture
     */
    public String getDateOfLecture() {
        return dateOfLecture;
    }

    /**
     * @param dateOfLecture the dateOfLecture to set
     */
    public void setDateOfLecture(String dateOfLecture) {
        this.dateOfLecture = dateOfLecture;
    }

    /**
     * @return the details of lecture
     */
    @Override
    public String toString() {
        return "lectureId= " + getLectureId() + ", CourseId= " + getCourse().getCourseId() + ", date= "
                + getDateOfLecture();
    }
}