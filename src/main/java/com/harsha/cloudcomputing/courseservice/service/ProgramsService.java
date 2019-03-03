package com.harsha.cloudcomputing.courseservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.harsha.cloudcomputing.courseservice.datamodel.InMemoryDatabase;
import com.harsha.cloudcomputing.courseservice.datamodel.Program;

/**
 * ProgramsService
 */
public class ProgramsService {

    private HashMap<Long, Program> program_Map = InMemoryDatabase.getProgramDB();

    public ProgramsService() {

    }

    public Program addProgram(Program program) {
        // establishes unique program name
        if (this.findByName(program.getName()) != null) {
            return null;
        }
        long nextAvailableId = program_Map.size() + 1;
        program.setId(nextAvailableId);
        program_Map.put(nextAvailableId, program);
        return program;
    }

    public Program addProgram(String name, String degreeLevel, int requiredCredits) {
        Program program = new Program(name, degreeLevel, requiredCredits);
        return this.addProgram(program);
    }

    public Stream<Program> getProgramStream() {
        return this.program_Map.values().stream().filter(p -> p != null);
    }

    public Program getProgram(long id) {
        return this.getProgramStream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public List<Program> limitPrograms(Stream<Program> programStream, int maxSize) {
        if (maxSize > 0) {
            programStream = programStream.limit(maxSize);
        }
        return programStream.collect(Collectors.toList());
    }

    public List<Program> getAllPrograms() {
        return this.limitPrograms(this.getProgramStream(), 0);
    }

    public Stream<Program> filterBy(Stream<Program> programStream, String degreeLevel) {
        return programStream.filter(p -> p.getDegreeLevel().equalsIgnoreCase(degreeLevel));
    }

    public Program findByName(String name) {
        return this.getProgramStream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public Stream<Program> getProgramsOfDegreeLevel(String degreeLevel) {
        return this.filterBy(this.getProgramStream(), degreeLevel);
    }

    public Program updateProgram(Program program, long id) {
        Program previousProgram = program_Map.get(id);
        program.setId(previousProgram.getId());
        program_Map.put(id, program);
        return program;
    }

    public Program deleteProgram(long id) {
        Program programToDelete = program_Map.get(id);
        program_Map.put(id, null);
        return programToDelete;
    }

}