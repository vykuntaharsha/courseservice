package com.harsha.cloudcomputing.courseservice.resources;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;

/**
 * TestResource (exposed at "test" path)
 */
@Api
@Path("/health")
public class TestResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent to the
     * client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getHealth() {

        Map<String, String> resultObj = new HashMap<>();
        resultObj.put("status", "good");
        resultObj.put("serverTime", new Date().toString());
        return resultObj;
    }
}
