package com.harsha.cloudcomputing.courseservice.service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.harsha.cloudcomputing.courseservice.datamodel.DynamoDBConnector;
import com.harsha.cloudcomputing.courseservice.datamodel.InMemoryDatabase;
import com.harsha.cloudcomputing.courseservice.datamodel.Professor;
import org.apache.log4j.Logger;

/**
 * ProfessorsService
 */
public class ProfessorsService {
    private static Logger log = Logger.getLogger(ProfessorsService.class);
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

        prof.setJoiningDate(getCurrentUTCTimeString());
        mapper.save(prof);
        return prof;
    }

    // Adding a professor
    public Professor addProfessor(String firstName, String lastName, String programId) {
        Professor prof =
                new Professor(null, firstName, lastName, programId, Instant.now().toString());
        return addProfessor(prof);
    }

    public Professor getProfessor(String id) {
        Professor prof = new Professor();
        prof.setId(id);
        DynamoDBQueryExpression<Professor> queryExpression =
                new DynamoDBQueryExpression<Professor>().withHashKeyValues(prof).withLimit(1);
        List<Professor> professors = mapper.query(Professor.class, queryExpression);
        try {
            return professors.get(0);
        } catch (IndexOutOfBoundsException e) {
            // pass
        }
        return null;
    }

    // Getting One Professor
    public Professor getProfessorWithProfessorId(String professorId) {
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":id", new AttributeValue().withS(professorId));

        DynamoDBQueryExpression<Professor> queryExpression =
                new DynamoDBQueryExpression<Professor>().withIndexName("professorId-index")
                        .withKeyConditionExpression("professorId = :id")
                        .withExpressionAttributeValues(eav).withConsistentRead(false).withLimit(1);

        List<Professor> professors = mapper.query(Professor.class, queryExpression);
        try {
            Professor prof = professors.get(0);
            prof = getProfessor(prof.getId());
            return prof;
        } catch (IndexOutOfBoundsException e) {
            // pass
        }
        return null;
    }

    // Deleting a professor
    public Professor deleteProfessor(String id) {
        Professor prof = getProfessor(id);
        if (prof != null) {
            mapper.delete(prof);
        }
        return prof;
    }

    // Updating Professor Info
    public Professor updateProfessorInformation(String id, Professor prof) {
        Professor oldProfObject = getProfessor(id);

        if (oldProfObject != null) {
            prof.setId(id);
            prof.setProfessorId(oldProfObject.getProfessorId());
            if (prof.getJoiningDate() == null) {
                prof.setJoiningDate(oldProfObject.getJoiningDate());
            }
            mapper.save(prof);
        }

        return prof;
    }

    public List<Professor> getProfessorsWith(String program, Integer year) {
        final DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        PaginatedScanList<Professor> professors = mapper.scan(Professor.class, scanExpression);
        professors.loadAllResults();
        return professors;
    }

    public List<Professor> getProfessorsOfProgram(String program) {
        final DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return null;
    }

    public List<Professor> getProfessorsJoinedInYear(Integer year) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":year", new AttributeValue().withS(year.toString()));
        log.info("Fetching professors with joining year: " + year);

        final DynamoDBScanExpression scanExpression =
                new DynamoDBScanExpression().withFilterExpression("begins_with(joiningDate, :year)")
                        .withExpressionAttributeValues(eav);

        List<Professor> professors = mapper.scan(Professor.class, scanExpression);

        log.info("Total results found: " + professors.size());
        return professors;
    }

    private String getCurrentUTCTimeString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return Instant.now().atZone(ZoneOffset.UTC).format(formatter);
    }
}
