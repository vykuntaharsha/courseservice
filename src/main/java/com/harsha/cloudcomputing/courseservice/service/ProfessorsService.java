package com.harsha.cloudcomputing.courseservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public void addProfessor(String firstName, String lastName, String programId, LocalDate joiningDate) {
        // Next Id
        long nextAvailableId = prof_Map.size() + 1;

        // Create a Professor Object
        Professor prof = new Professor(firstName + lastName, firstName, lastName, programId, joiningDate);
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

    public Stream<Professor> getProfessorStream() {
        return prof_Map.values().stream().filter(p -> p != null);
    }

    public Stream<Professor> filterBy(Stream<Professor> professorStream, String program) {
        return professorStream.filter(p -> p.getProgram().equalsIgnoreCase(program));
    }

    public Stream<Professor> filterBy(Stream<Professor> professorStream, int year) {
        return professorStream.filter(p -> p.getJoiningDate().getYear() == year);
    }

    /**
     * @param program name or program Id to match
     * @return list of professors with matching program
     */
    public Stream<Professor> getProfessorsByProgram(String program) {
        return this.filterBy(this.getProfessorStream(), program);
    }

    public Stream<Professor> getProfessorsJoinedInYear(int year) {
        return this.filterBy(this.getProfessorStream(), year);
    }

    public List<Professor> limitProfessors(Stream<Professor> professorStream, int maxSize) {
        if (maxSize != 0) {
            professorStream = professorStream.limit(maxSize);
        }
        return professorStream.collect(Collectors.toList());
    }
}