package com.harsha.cloudcomputing.courseservice.util;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * ApplicationUtil
 */
public class ApplicationUtil {

    public static String getCurrentUTCTimeString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return Instant.now().atZone(ZoneOffset.UTC).format(formatter);
    }
}
