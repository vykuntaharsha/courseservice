package com.harsha.cloudcomputing.courseservice.datamodel;

import java.util.HashMap;

/**
 * InMemoryDatabase
 */
public class InMemoryDatabase {
    private static HashMap<Long, Professor> professorDB = new HashMap<>();

    public static HashMap<Long, Professor> getProfessorDB() {
        return professorDB;
    }
}