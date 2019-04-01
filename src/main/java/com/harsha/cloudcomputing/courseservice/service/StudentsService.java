package com.harsha.cloudcomputing.courseservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.harsha.cloudcomputing.courseservice.datamodel.DynamoDBConnector;
import com.harsha.cloudcomputing.courseservice.datamodel.Student;
import com.harsha.cloudcomputing.courseservice.util.ApplicationUtil;

/**
 * StudentsService
 */
public class StudentsService {

    DynamoDBMapper mapper;

    public StudentsService() {
        DynamoDBConnector.init();
        mapper = new DynamoDBMapper(new DynamoDBConnector().getClient());
    }

    public Student createStudent(Student student) {
        String studentId = student.getLastName() + "-" + student.getFirstName();
        student.setStudentId(studentId);
        student.setJoiningDate(ApplicationUtil.getCurrentUTCTimeString());
        mapper.save(student);
        return student;
    }

    /**
     * @return the list of all available students
     */
    public List<Student> getStudents() {
        PaginatedList<Student> students = mapper.scan(Student.class, new DynamoDBScanExpression());
        students.loadAllResults();
        return students;
    }

    /**
     * @param id to retrive student from map
     * @return the student object
     */
    public Student getStudent(String id) {
        return mapper.load(Student.class, id);
    }

    public Student getStudentWithStudentId(String studentId) {
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":id", new AttributeValue().withS(studentId));

        final DynamoDBQueryExpression<Student> queryExpression =
                new DynamoDBQueryExpression<Student>().withIndexName("studentId-index")
                        .withKeyConditionExpression("studentId = :id")
                        .withExpressionAttributeValues(eav).withConsistentRead(false).withLimit(1);

        List<Student> students = mapper.query(Student.class, queryExpression);
        try {
            Student student = students.get(0);
            student = getStudent(student.getId());
            return student;
        } catch (IndexOutOfBoundsException e) {
            // pass
        }
        return null;
    }

    /**
     * @param id      to update student of corresponding id
     * @param student information to update
     * @return the updated student object
     */
    public Student updateStudent(String id, Student student) {
        Student studentToUpdate = getStudent(id);
        if (studentToUpdate == null) {
            return null;
        }
        student.setStudentId(studentToUpdate.getStudentId());
        student.setId(studentToUpdate.getId());
        student.setCoursesEnrolled(studentToUpdate.getCoursesEnrolled());
        if (student.getJoiningDate() == null) {
            student.setJoiningDate(studentToUpdate.getJoiningDate());
        }
        mapper.save(student);

        return student;
    }

    public Student saveStudent(Student student) {
        mapper.save(student);
        return student;
    }

    /**
     * @param id to delete student from map
     * @return the deleted student obj
     */
    public Student deleteStudent(String id) {
        Student studentToDelete = getStudent(id);
        if (studentToDelete == null) {
            return null;
        }
        mapper.delete(studentToDelete);
        return studentToDelete;
    }

    /**
     * 
     * @param program to match
     * @return list of students with matching program
     */
    public List<Student> getStudentsByProgram(String program) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":program", new AttributeValue().withS(program));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("program = :program").withExpressionAttributeValues(eav);

        PaginatedList<Student> students = mapper.scan(Student.class, scanExpression);
        students.loadAllResults();
        return students;
    }
}
