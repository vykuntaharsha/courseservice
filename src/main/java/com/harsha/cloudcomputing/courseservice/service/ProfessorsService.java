package com.harsha.cloudcomputing.courseservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.harsha.cloudcomputing.courseservice.datamodel.InMemoryDatabase;
import com.harsha.cloudcomputing.courseservice.datamodel.Professor;

/**
 * ProfessorsService
 */
public class ProfessorsService {
    static HashMap<Long, Professor> prof_Map = InMemoryDatabase.getProfessorDB();

    public ProfessorsService() {
    }

    // Getting a list of all professor
    // GET "..webapi/professors"
    public List<Professor> getAllProfessors() {
        // Getting the list
        ArrayList<Professor> list = new ArrayList<>();
        for (Professor prof : prof_Map.values()) {
            list.add(prof);
        }
        return list;
    }

    // Adding a professor
    public Professor addProfessor(Professor prof) {
        // Next Id
        long nextAvailableId = prof_Map.size() + 1;

        // Updating prof id
        prof.setId(nextAvailableId);
        prof_Map.put(nextAvailableId, prof);
        return prof;
    }

    // Adding a professor
    public void addProfessor(String firstName, String lastName, String department, Date joiningDate) {
        // Next Id
        long nextAvailableId = prof_Map.size() + 1;

        // Create a Professor Object
        Professor prof = new Professor(firstName + lastName, firstName, lastName, department, joiningDate.toString());
        prof.setId(nextAvailableId);
        prof_Map.put(nextAvailableId, prof);
    }

    // Getting One Professor
    public Professor getProfessor(Long profId) {

        // Simple HashKey Load
        Professor prof2 = prof_Map.get(profId);
        System.out.println("Item retrieved:");
        System.out.println(prof2.toString());

        return prof2;
    }

    // Deleting a professor
    public Professor deleteProfessor(Long profId) {
        Professor deletedProfDetails = prof_Map.get(profId);
        prof_Map.put(profId, null);
        return deletedProfDetails;
    }

    // Updating Professor Info
    public Professor updateProfessorInformation(Long id, Professor prof) {
        Professor oldProfObject = prof_Map.get(id);

        if (oldProfObject == null) {
            return null;
        }
        // updating the professor obj
        prof.setProfessorId(oldProfObject.getProfessorId());
        prof.setId(oldProfObject.getId());
        prof_Map.put(id, prof);
        return prof;
    }

    // Get professors in a department
    public List<Professor> getProfessorsByDepartment(String department) {
        // Getting the list
        ArrayList<Professor> list = new ArrayList<>();
        for (Professor prof : prof_Map.values()) {
            if (prof != null && prof.getDepartment().equalsIgnoreCase(department)) {
                list.add(prof);
            }
        }
        return list;
    }

    // Get professors for a year with a size limit

}