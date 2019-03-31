package com.harsha.cloudcomputing.courseservice.datamodel;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

/**
 * DynamoDBConnector
 */
public class DynamoDBConnector {
    static AmazonDynamoDB dynamoDb;

    public static void init() {
        if (dynamoDb == null) {
            dynamoDb = AmazonDynamoDBClientBuilder.standard()
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                            "http://localhost:8000", "us-west-2"))
                    .build();
        }
    }

    public AmazonDynamoDB getClient() {
        return dynamoDb;
    }
}