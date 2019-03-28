package com.harsha.cloudcomputing.courseservice.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.harsha.cloudcomputing.courseservice.datamodel.DynamoDBConnector;
import com.harsha.cloudcomputing.courseservice.datamodel.InMemoryDatabase;
import com.harsha.cloudcomputing.courseservice.datamodel.Professor;

/**
 * ProfessorsService
 */
public class ProfessorsService {
    static HashMap<Long, Professor> prof_Map = InMemoryDatabase.getProfessorDB();

    AmazonDynamoDB dynamoDB;
    DynamoDBMapper mapper;

    public ProfessorsService() {
        DynamoDBConnector.init();
        mapper = new DynamoDBMapper(new DynamoDBConnector().getClient());
    }

    // Getting a list of all professor
    // GET "..webapi/professors"
    public List<Professor> getAllProfessors() {
        // Getting the list
        PaginatedScanList<Professor> scanList =
                mapper.scan(Professor.class, new DynamoDBScanExpression());
        scanList.loadAllResults();
        return scanList;
    }

    public List<Professor> getProfessors(Map<String, AttributeValue> lastEvaluatedKey,
            Integer limit) {
        final DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withLimit(limit)
                .withExclusiveStartKey(lastEvaluatedKey);

        PaginatedScanList<Professor> scanList = mapper.scan(Professor.class, scanExpression);
        return scanList;
    }

    // Adding a professor
    public Professor addProfessor(Professor prof) {
        // Updating prof id
        prof.setProfessorId(prof.getFirstName() + "-" + prof.getLastName());
        prof.setJoiningDate(LocalDate.now().toString());
        mapper.save(prof);
        return prof;
    }

    // Adding a professor
    public Professor addProfessor(String firstName, String lastName, String programId) {
        Professor prof =
                new Professor(null, firstName, lastName, programId, LocalDate.now().toString());
        return addProfessor(prof);
    }

    // Getting One Professor
    public Professor getProfessor(String professorId) {
        Professor prof = null;

        return prof;
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
        return professorStream.filter(p -> LocalDate.parse(p.getJoiningDate()).getYear() == year);
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
