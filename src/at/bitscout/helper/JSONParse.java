package at.bitscout.helper;

import at.bitscout.model.CourseTimePeriod;
import at.bitscout.model.Currency;
import at.bitscout.model.Rate;
import at.bitscout.model.CurrencyList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

/**
 * @author Bandalo
 * @version 1.2
 * Description: Parses a JSON String into Java Objects
 */

public class JSONParse {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static Rate rate, rate2;
    private static List<CourseTimePeriod> courseTimePeriods;
    private static List<Rate> rateList;
    private static List<Currency> currencies;



    /**
     * @param apiString Response string from api
     * @return Newly created rate
     */
    //Parses the JSON String to get the values of course rate, time, etc.
    public static Rate parseJSON(String apiString) throws JsonProcessingException {
        //Ignores every not known property of the JSON
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        rate = objectMapper.readValue(apiString, Rate.class);
        rate2 = new Rate(rate.getAsset_id_base(), rate.getAsset_id_quote(), rate.getRate(), rate.getTime());
        return rate2;
    }



    /**
     * @param apiString Response string from api
     * @return Time period of rate
     */
    //Parses the JSON of a specific time periode
    public static List parseJSON(String apiString, String time) throws JsonProcessingException {
        //Ignores every not known property of the JSON
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //Puts the objects of the json array string in a list
        courseTimePeriods = Arrays.asList(objectMapper.readValue(apiString, CourseTimePeriod[].class));
        //Output of the courseList

        return courseTimePeriods;
    }

    /**
     * @param apiString Response string from api
     */
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

    /**
     * @param apiString Response string from api
     * @return List of all rates
     */

    //Parses the some Crypto currencies for the left side
    public static List parseJSONSide(String apiString) throws JsonProcessingException {
        //Ignores every not known property of the JSON
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //Puts the objects of the json array string in a list
        rateList = Arrays.asList(objectMapper.readValue(apiString, Rate[].class));
        //System.out.println(courseList.get(0).getAsset_id() + " " + courseList.get(0).getPriceInUSD());
        return rateList;
    }
}
