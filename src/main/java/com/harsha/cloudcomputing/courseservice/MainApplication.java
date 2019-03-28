package com.harsha.cloudcomputing.courseservice;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import com.harsha.cloudcomputing.courseservice.datamodel.DynamoDBTables;
import org.apache.log4j.Logger;


/**
 * MainApplication
 */
@ApplicationPath("/")
public class MainApplication extends Application {

    public MainApplication() {
        super();
        try {
            DynamoDBTables.createTablesIfNotExist();
        } catch (Exception e) {
            Logger.getLogger(MainApplication.class).error("DynamoDB tables creation failed");
        }
    }

}
