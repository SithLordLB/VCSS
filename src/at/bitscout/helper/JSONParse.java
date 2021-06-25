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

/** Parses a JSON String into Java Objects
 * @author Bandalo
 * @version 1.2
 */

public class JSONParse {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static Rate rate, rate2;
    private static List<CourseTimePeriod> courseTimePeriods;
    private static List<Rate> rateList;
    private static List<Currency> currencies;



    /** Parses the JSON String to get the values of course rate, time, etc.
     * @param apiString Response string from api
     * @return Newly created rate
     */
    public static Rate parseJSON(String apiString) throws JsonProcessingException {
        //Ignores every not known property of the JSON
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        rate = objectMapper.readValue(apiString, Rate.class);
        rate2 = new Rate(rate.getAsset_id_base(), rate.getAsset_id_quote(), rate.getRate(), rate.getTime());
        return rate2;
    }



    /** Parses the JSON of a specific time periode
     * @param apiString Response string from api
     * @return Time period of rate
     */
    public static List parseJSON(String apiString, String time) throws JsonProcessingException {
        //Ignores every not known property of the JSON
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //Puts the objects of the json array string in a list
        courseTimePeriods = Arrays.asList(objectMapper.readValue(apiString, CourseTimePeriod[].class));
        //Output of the courseList

        return courseTimePeriods;
    }

    /** Parses the assets (Crypto and Normal currencies)
     * @param apiString Response string from api
     */

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

    /** Parses the some Crypto currencies for the left side
     * @param apiString Response string from api
     * @return List of all rates
     */

    public static List parseJSONSide(String apiString) throws JsonProcessingException {
        //Ignores every not known property of the JSON
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //Puts the objects of the json array string in a list
        rateList = Arrays.asList(objectMapper.readValue(apiString, Rate[].class));
        //System.out.println(courseList.get(0).getAsset_id() + " " + courseList.get(0).getPriceInUSD());
        return rateList;
    }
}
