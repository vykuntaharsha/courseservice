package com.harsha.cloudcomputing.courseservice.datamodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Program
 */
public class Program {

    @JsonIgnore
    private Long id;
    private String name;
    private String degreeLevel;
    private int requiredCredits;

    public Program() {

    }

    /**
     * 
     * @param name            the name to set
     * @param degreeLevel     the degreeLevel to set
     * @param requiredCredits the requiredCredits to set
     */
    public Program(String name, String degreeLevel, int requiredCredits) {
        this.setName(name);
        this.setDegreeLevel(degreeLevel);
        this.setRequiredCredits(requiredCredits);
    }

    /**
     * @return the degreeLevel
     */
    public String getDegreeLevel() {
        return degreeLevel;
    }

    /**
     * @param degreeLevel the degreeLevel to set
     */
    public void setDegreeLevel(String degreeLevel) {
        this.degreeLevel = degreeLevel;
    }

    /**
     * @return the requiredCredits
     */
    public int getRequiredCredits() {
        return requiredCredits;
    }

    /**
     * @param requiredCredits the requiredCredits to set
     */
    public void setRequiredCredits(int requiredCredits) {
        this.requiredCredits = requiredCredits;
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

    @Override
    public String toString() {
        return "Program: " + this.getName() + ", degreeLevel= " + this.getDegreeLevel() + ", requiredCredits: "
                + this.getRequiredCredits();
    }
}