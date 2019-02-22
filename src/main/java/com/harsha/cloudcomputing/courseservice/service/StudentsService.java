package com.harsha.cloudcomputing.courseservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.harsha.cloudcomputing.courseservice.datamodel.Course;
import com.harsha.cloudcomputing.courseservice.datamodel.InMemoryDatabase;
import com.harsha.cloudcomputing.courseservice.datamodel.Student;

/**
 * StudentsService
 */
public class StudentsService {
    private static HashMap<Long, Student> student_Map = InMemoryDatabase.getStudentDB();

    public StudentsService() {

    }

    /**
     * @return the list of all available students
     */
    public List<Student> getStudents() {
        return student_Map.values().stream().filter(s -> s != null).collect(Collectors.toList());
    }

    /**
     * Adding a student
     * 
     * @param firstName   the firstName of a student to set
     * @param lastName    the lastName of a student to set
     * @param image       the image of a student to set
     * @param program     the program of a student to set
     * @param joiningDate the joiningDate of a student to set
     * @return newly created student
     */
    public Student addStudent(String firstName, String lastName, String image, String program, Date joiningDate) {
        long nextAvailableId = student_Map.size() + 1;
        Student student = new Student(firstName + "-" + lastName, firstName, lastName, image, program,
                new ArrayList<>(), joiningDate.toString());
        student.setId(nextAvailableId);
        student_Map.put(nextAvailableId, student);
        return student;
    }

    /**
     * @param id to retrive student from map
     * @return the student object
     */
    public Student getStudent(Long id) {
        Student student = student_Map.get(id);
        System.out.println("Item received:");
        System.out.println(student);
        return student;
    }

    /**
     * @param id to delete student from map
     * @return the deleted student obj
     */
    public Student deleteStudent(Long id) {
        Student deletedStudentObj = student_Map.get(id);
        student_Map.put(id, null);
        return deletedStudentObj;
    }

    /**
     * @param id      to update student of corresponding id
     * @param student information to update
     * @return the updated student object
     */
    public Student updateStudentInformation(Long id, Student student) {
        Student studentToUpdate = student_Map.get(id);
        student.setStudentId(studentToUpdate.getStudentId());
        student_Map.put(id, student);
        return student;
    }

    /**
     * 
     * @param program to match
     * @return list of students with matching program
     */
    public List<Student> getStudentsByProgram(String program) {
        return student_Map.values().stream().filter(s -> s != null)
                .filter(s -> s.getProgram().equalsIgnoreCase(program)).collect(Collectors.toList());
    }

    /**
     * enrolls the student to a particular course
     * 
     * @param id     of student to enroll
     * @param course for the student to enroll
     * @return the enrolled student object
     */
    public Student enrollStudentToCourse(Long id, Course course) {
        Student studentToEnroll = student_Map.get(id);
        studentToEnroll.getCoursesEnrolled().add(course);
        return studentToEnroll;
    }

    /**
     * 
     * @param id     of the student to disenroll
     * @param course for the student to disenroll
     * @return the disenrolled student object
     */
    public Student disenrollStudentFromCourse(Long id, Course course) {
        Student studentToDisenroll = student_Map.get(id);
        studentToDisenroll.getCoursesEnrolled().removeIf(c -> c.getId() == course.getId());
        return studentToDisenroll;
    }

}