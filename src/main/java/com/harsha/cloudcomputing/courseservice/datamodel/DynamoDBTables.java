package com.harsha.cloudcomputing.courseservice.datamodel;

import java.util.Arrays;
import java.util.List;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.GlobalSecondaryIndex;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import org.apache.log4j.Logger;


/**
 * DynamoDBTables
 */
public class DynamoDBTables {
    static List<Class<?>> tableClasses = Arrays.asList(Professor.class, Course.class, Student.class,
            Board.class, Announcement.class);
    private static Logger log = Logger.getLogger(DynamoDBTables.class);

    public static void createTablesIfNotExist() {
        DynamoDBConnector.init();
        AmazonDynamoDB dynamoDb = new DynamoDBConnector().getClient();
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDb);
        log.info("Creating Tables on DynamoDB");
        for (Class<?> table : tableClasses) {
            CreateTableRequest createTableRequest = mapper.generateCreateTableRequest(table);

            createTableRequest.setProvisionedThroughput(new ProvisionedThroughput(2L, 2L));
            for (GlobalSecondaryIndex index : createTableRequest.getGlobalSecondaryIndexes()) {
                index.setProvisionedThroughput(new ProvisionedThroughput(2L, 2L));
            }

            if (TableUtils.createTableIfNotExists(dynamoDb, createTableRequest)) {
                log.info("Created table:" + createTableRequest.getTableName());
                try {
                    TableUtils.waitUntilActive(dynamoDb, createTableRequest.getTableName());
                } catch (InterruptedException e) {
                    log.error(e.toString());
                }
            }
        }

        log.info("Done creating tables");
    }
}
