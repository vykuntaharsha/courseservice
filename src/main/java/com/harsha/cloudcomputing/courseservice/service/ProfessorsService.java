package com.harsha.cloudcomputing.courseservice.service;

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
import com.harsha.cloudcomputing.courseservice.datamodel.Professor;
import com.harsha.cloudcomputing.courseservice.util.ApplicationUtil;
import org.apache.log4j.Logger;

/**
 * ProfessorsService
 */
public class ProfessorsService {
    private static Logger log = Logger.getLogger(ProfessorsService.class);

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

    // Adding a professor
    public Professor addProfessor(Professor prof) {
        // Updating prof id
        prof.setProfessorId(prof.getFirstName() + "-" + prof.getLastName());

        prof.setJoiningDate(ApplicationUtil.getCurrentUTCTimeString());
        mapper.save(prof);
        return prof;
    }

    // Adding a professor
    public Professor addProfessor(String firstName, String lastName, String programId) {
        Professor prof = new Professor(null, firstName, lastName, programId,
                ApplicationUtil.getCurrentUTCTimeString());
        return addProfessor(prof);
    }

    public Professor getProfessor(String id) {
        Professor prof = new Professor();
        prof.setId(id);
        final DynamoDBQueryExpression<Professor> queryExpression =
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

        final DynamoDBQueryExpression<Professor> queryExpression =
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
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":program", new AttributeValue().withS(program));
        eav.put(":year", new AttributeValue().withS(year.toString()));

        final DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("begins_with(joiningDate, :year) and program = :program")
                .withExpressionAttributeValues(eav);
        PaginatedScanList<Professor> professors = mapper.scan(Professor.class, scanExpression);
        professors.loadAllResults();
        return professors;
    }

    public List<Professor> getProfessorsOfProgram(String program) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":program", new AttributeValue().withS(program));
        final DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("program = :program").withExpressionAttributeValues(eav);
        PaginatedScanList<Professor> professors = mapper.scan(Professor.class, scanExpression);
        professors.loadAllResults();
        return professors;
    }

    public List<Professor> getProfessorsJoinedInYear(Integer year) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":year", new AttributeValue().withS(year.toString()));

        final DynamoDBScanExpression scanExpression =
                new DynamoDBScanExpression().withFilterExpression("begins_with(joiningDate, :year)")
                        .withExpressionAttributeValues(eav);

        PaginatedScanList<Professor> professors = mapper.scan(Professor.class, scanExpression);
        professors.loadAllResults();
        return professors;
    }
}
