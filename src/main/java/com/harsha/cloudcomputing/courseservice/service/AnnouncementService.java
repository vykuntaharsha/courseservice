package com.harsha.cloudcomputing.courseservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.KeyPair;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.harsha.cloudcomputing.courseservice.datamodel.Announcement;
import com.harsha.cloudcomputing.courseservice.datamodel.DynamoDBConnector;
import com.harsha.cloudcomputing.courseservice.util.ApplicationConstants;
import com.harsha.cloudcomputing.courseservice.util.ExceedsMaxCharactersException;

/**
 * AnnouncementService
 */
public class AnnouncementService {
    DynamoDBMapper mapper;

    public AnnouncementService() {
        DynamoDBConnector.init();
        mapper = new DynamoDBMapper(new DynamoDBConnector().getClient());
    }

    public Announcement createAnnouncement(Announcement announcement)
            throws ExceedsMaxCharactersException {
        if (announcement.getAnnouncementText()
                .length() > ApplicationConstants.ANNOUNCEMENT_CHAR_LIMIT) {
            throw new ExceedsMaxCharactersException(ApplicationConstants.ANNOUNCEMENT_CHAR_LIMIT);
        }
        String announcementId = "announcement-" + UUID.randomUUID();
        announcement.setAnnouncementId(announcementId);
        mapper.save(announcement);
        return announcement;
    }

    public List<Announcement> getAnnouncements() {
        PaginatedList<Announcement> announcements =
                mapper.scan(Announcement.class, new DynamoDBScanExpression());
        announcements.loadAllResults();
        return announcements;
    }

    public Announcement getAnnouncement(String id) {
        return mapper.load(Announcement.class, id);
    }

    public Announcement updateAnnouncement(String id, Announcement announcement)
            throws ExceedsMaxCharactersException {
        if (announcement.getAnnouncementText()
                .length() > ApplicationConstants.ANNOUNCEMENT_CHAR_LIMIT) {
            throw new ExceedsMaxCharactersException(ApplicationConstants.ANNOUNCEMENT_CHAR_LIMIT);
        }
        Announcement announcementToUpdate = getAnnouncement(id);
        if (announcementToUpdate == null) {
            return null;
        }

        announcement.setAnnouncementId(announcementToUpdate.getAnnouncementId());
        announcement.setId(announcementToUpdate.getId());
        mapper.save(announcement);
        return announcement;
    }

    public Announcement deleteAnnouncement(String id) {
        Announcement announcementToDelete = getAnnouncement(id);
        if (announcementToDelete == null) {
            return null;
        }

        mapper.delete(announcementToDelete);
        return announcementToDelete;
    }

    @SuppressWarnings("unchecked")
    public List<Announcement> getAnnouncementsOfBoard(String boardId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":boardId", new AttributeValue().withS(boardId));

        DynamoDBQueryExpression<Announcement> queryExpression =
                new DynamoDBQueryExpression<Announcement>().withIndexName("AnnouncementBoard-index")
                        .withKeyConditionExpression("boardId = :boardId")
                        .withExpressionAttributeValues(eav).withConsistentRead(false);

        PaginatedList<Announcement> queryList = mapper.query(Announcement.class, queryExpression);
        queryList.loadAllResults();

        Map<Class<?>, List<KeyPair>> itemsToGet = new HashMap<>();

        List<KeyPair> keyPairs = queryList.stream().map(Announcement::getId)
                .map(new KeyPair()::withHashKey).collect(Collectors.toList());

        itemsToGet.put(Announcement.class, keyPairs);

        Map<String, List<Object>> batchLoad = mapper.batchLoad(itemsToGet);
        return (List<Announcement>) (List<?>) batchLoad.get("announcements");
    }

}
