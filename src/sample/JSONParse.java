package sample;
/*
    Author: LB
    Created on: 14.05.2021
    Changed on: 20.05.2021
    Changed from: LB
    Description: Controller class for managing code to UI communication
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONParse {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static Course course, course2;

    //Parses the JSON String to get the values of course rate, time, etc.
    public static Course parseJSON(String apiSring) throws JsonProcessingException {
        //Ignores every not known property of the JSON
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        course = objectMapper.readValue(apiSring, sample.Course.class);
        course2 = new Course(course.getAsset_id_base(), course.getAsset_id_quote(), course.getRate(), course.getTime());

        return course2;
    }
}
