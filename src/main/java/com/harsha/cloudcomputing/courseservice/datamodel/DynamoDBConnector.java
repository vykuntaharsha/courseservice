package com.harsha.cloudcomputing.courseservice.datamodel;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

/**
 * DynamoDBConnector
 */
public class DynamoDBConnector {
    static AmazonDynamoDB dynamoDb;

    public static void init() {
        if (dynamoDb == null) {
            InstanceProfileCredentialsProvider credentialsProvider =
                    InstanceProfileCredentialsProvider.getInstance();
            dynamoDb = AmazonDynamoDBClientBuilder.standard().withCredentials(credentialsProvider)
                    .withRegion("us-west-2").build();
        }
    }

    public AmazonDynamoDB getClient() {
        return dynamoDb;
    }
}
