package com.harsha.cloudcomputing.courseservice.datamodel;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * Board
 */
@DynamoDBTable(tableName = "boards")
public class Board {

    private String id;
    private String boardId;
    private String courseId;

    public Board() {

    }

    public Board(String boardId, String courseId) {
        this.boardId = boardId;
        this.courseId = courseId;
    }

    /**
     * @return the id
     */
    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the boardId
     */
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "boardId-index", attributeName = "boardId")
    public String getBoardId() {
        return boardId;
    }

    /**
     * @param boardId the boardId to set
     */
    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    /**
     * @return the courseId
     */
    @DynamoDBAttribute(attributeName = "courseId")
    public String getCourseId() {
        return courseId;
    }

    /**
     * @param courseId the courseId to set
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @DynamoDBIgnore
    @Override
    public String toString() {
        return "boardId = " + getBoardId() + ", courseId = " + getCourseId();
    }

}
