package com.harsha.cloudcomputing.courseservice.datamodel;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Professor
 */
public class Professor {

    @JsonIgnore
    private Long id;
    private String firstName;
    private String lastName;
    private String program;
    @JsonIgnore
    private String professorId;
    @JsonIgnore
    private LocalDate joiningDate;

    public Professor() {

    }

    public Professor(String professorId, String firstName, String lastName, String program, LocalDate joiningDate) {
        this.professorId = professorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.program = program;
        this.joiningDate = joiningDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getProgram() {
        return this.program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    @JsonProperty
    public String getProfessorId() {
        return professorId;
    }

    @JsonIgnore
    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    @JsonProperty
    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    @JsonIgnore
    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    @JsonProperty
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "ProfId=" + getProfessorId() + ", firstName=" + getFirstName() + ", program=" + getProgram()
                + ", joiningDate=" + getJoiningDate();
    }
}