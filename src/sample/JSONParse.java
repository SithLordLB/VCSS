package sample;
/*
    Author: LB
    Created on: 14.05.2021
    Changed on: 12.06.2021
    Changed from: LB
    Description: Controller class for managing code to UI communication
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

public class JSONParse {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static Rate rate, rate2;
    private static List<CourseTimePeriod> courseTimePeriods;
    private static List<Rate> rateList;
    private static List<Currency> currencies;

    //Parses the JSON String to get the values of course rate, time, etc.
    public static Rate parseJSON(String apiSring) throws JsonProcessingException {
        //Ignores every not known property of the JSON
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        rate = objectMapper.readValue(apiSring, Rate.class);
        rate2 = new Rate(rate.getAsset_id_base(), rate.getAsset_id_quote(), rate.getRate(), rate.getTime());
        return rate2;
    }

    //Parses the JSON of a specific time periode
    public static List parseJSON(String apiString, String time) throws JsonProcessingException {
        //Ignores every not known property of the JSON
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //Puts the objects of the json array string in a list
        courseTimePeriods = Arrays.asList(objectMapper.readValue(apiString, CourseTimePeriod[].class));
        //Output of the courseList
        for(CourseTimePeriod list: courseTimePeriods){
            System.out.println(list.getTime_period_start() + " " + list.getTime_period_end());
        }

        return courseTimePeriods;
    }

    //Parses the assets (Crypto and Normal currencies)
    public static void parseJSONAsset(String apiString) throws JsonProcessingException {
        //Ignores every not known property of the JSON
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //Puts the objects of the json array string in a list
        currencies = Arrays.asList(objectMapper.readValue(apiString, Currency[].class));

        //Sorts out crypto and fiat
        for (Currency currency: currencies){
            CurrencyList.addCurrency(currency.getIsoCode(), currency.getName(), currency.getIsCrypto());
        }
        System.out.println();
    }

    //Parses the some Crypto currencies for the left side fuck
    public static List parseJSONSide(String apiString) throws JsonProcessingException {
        //Ignores every not known property of the JSON
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //Puts the objects of the json array string in a list
        rateList = Arrays.asList(objectMapper.readValue(apiString, Rate[].class));
        //System.out.println(courseList.get(0).getAsset_id() + " " + courseList.get(0).getPriceInUSD());
        return rateList;
    }
}
