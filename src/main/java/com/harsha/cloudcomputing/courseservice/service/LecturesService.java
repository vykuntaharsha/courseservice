package com.harsha.cloudcomputing.courseservice.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.harsha.cloudcomputing.courseservice.datamodel.Course;
import com.harsha.cloudcomputing.courseservice.datamodel.InMemoryDatabase;
import com.harsha.cloudcomputing.courseservice.datamodel.Lecture;

import org.apache.commons.lang3.StringUtils;

/**
 * LecturesService
 */
public class LecturesService {
    private HashMap<Long, Lecture> lecture_Map = InMemoryDatabase.getLectureDB();

    public LecturesService() {

    }

    /**
     * @param lecture to add new lecture
     * @return newly added lecture
     */
    public Lecture addLecture(Lecture lecture) {
        long nextAvailableId = lecture_Map.size() + 1;
        String lectureId = "L+" + StringUtils.leftPad(String.valueOf(nextAvailableId), 4, "0");
        lecture.setLectureId(lectureId);
        lecture.setId(nextAvailableId);
        lecture_Map.put(nextAvailableId, lecture);
        return lecture;
    }

    /**
     * 
     * @param course        to set for the lecture
     * @param dateOfLecture to set date of lecture
     * @return newly created lecture
     */
    public Lecture addLecture(Course course, Date dateOfLecture) {
        long nextAvailableId = lecture_Map.size() + 1;
        String lectureId = "L+" + StringUtils.leftPad(String.valueOf(nextAvailableId), 4, "0");
        Lecture lecture = new Lecture(lectureId, course, dateOfLecture.toString());
        lecture.setId(nextAvailableId);
        lecture_Map.put(nextAvailableId, lecture);
        return lecture;
    }

    /**
     * @param id of the course
     * @return lectures assosciated for the course
     */
    public List<Lecture> getLecturesOfCourse(Long id) {
        return lecture_Map.values().stream().filter(l -> l != null).filter(l -> l.getCourse().getId() == id)
                .collect(Collectors.toList());
    }

    /**
     * @param id of the lecture
     * @return the lecture assosciated with the id
     */
    public Lecture getLecture(Long id) {
        Lecture lecture = lecture_Map.get(id);
        System.out.println("Item received:");
        System.out.println(lecture);
        return lecture;
    }

    /**
     * 
     * @param id to delete lecture
     * @return the deleted lecture
     */
    public Lecture deleteLecture(Long id) {
        Lecture lectureToDelete = lecture_Map.get(id);
        lecture_Map.put(id, null);
        return lectureToDelete;
    }

    /**
     * 
     * @param id      to update lecture
     * @param lecture with updated fields
     * @return updated lecture
     */
    public Lecture updateLectureInformation(Long id, Lecture lecture) {
        Lecture lectureToUpdate = lecture_Map.get(id);
        lecture.setLectureId(lectureToUpdate.getLectureId());
        lecture.setId(lectureToUpdate.getId());
        lecture_Map.put(id, lecture);
        return lecture;
    }

}