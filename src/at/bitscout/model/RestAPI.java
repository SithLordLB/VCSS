package at.bitscout.model;
/*
    Author: LB
    Created on: 03.05.2021
    Changed on: 13.05.2021
    Changed from: LB
    Description: This class provides the information for the currencies, exchanges,...
 */

import at.bitscout.helper.JSONParse;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Bandalo
 * @version 1.3
 * Description: This class provides the information for the currencies, exchanges,...
 */

public class RestAPI {
    private Rate rate;
    private List<Rate> rateList;
    static final HttpClient httpClient
            = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .build();
    HttpRequest request;
    private final String apiKey = "A3503CC6-496C-4446-9A50-6823ABD95A85";       //API Key for tests



    //Gets with the filter some Crypto to create a List with them
    public List<Rate> getAPICourse() {
        try {
            HttpRequest request;
            request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://rest.coinapi.io/v1/assets?filter_asset_id=" + URLEncoder.encode("{;BTC;ETH;LTC;DOGE;XRP;ADA;}", "UTF-8")))
                    .headers("X-CoinAPI-Key", apiKey, "Accept", "application/json")         //Schlüssel in einem File klatschen und User cock blocken amena koi fick
                    .build();

            //The content is saved it response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            //Outputs the status code
            System.out.println(response.statusCode());

            //Outputs the content of the request
            //System.out.println(response.body());

            //Calls parseJSON
            rateList = JSONParse.parseJSONSide(response.body());


        } catch (IOException iOE) {
            System.out.println("ERROR: " + iOE.toString());

        } catch (InterruptedException iE) {
            System.out.println("ERROR: " + iE.toString());
        }

        return rateList;
    }

    /**
     *
     * @param currency1 Variable of first currency
     * @param currency2 Variable of second currency
     * @return Conversion rate gets returned
     */
    //Gets the course of 2 given currencies
    public Rate getAPICourse(String currency1, String currency2) {
        try {
            HttpRequest request;
            request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://rest.coinapi.io/v1/exchangerate/" + currency1 + "/" + currency2))
                    .headers("X-CoinAPI-Key", apiKey, "Accept", "application/json")         //Schlüssel in einem File klatschen und User cock blocken amena koi fick
                    .build();

            //The content is saved it response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            //Outputs the status code
            System.out.println(response.statusCode());

            //Outputs the content of the request
            System.out.println(response.body());

            response.body();
            //Calls parseJSON
            rate = JSONParse.parseJSON(response.body());


        } catch (IOException iOE) {
            System.out.println("ERROR: " + iOE.toString());

        } catch (InterruptedException iE) {
            System.out.println("ERROR: " + iE.toString());
        }

        return rate;
    }

    /**
     *
     * @param currency1 Variable of first currency
     * @param currency2 Variable of second currency
     * @param time Time period
     * @return Returns list of time periods
     */
    //Gets the exchange rate in a time periode
    public List getAPICourse(String currency1, String currency2, String time) {
        try {
            switch (time) {
                //response the rate from the past 24h (in 1h steps)
                case "1 Day":
                    request = HttpRequest.newBuilder()
                            .GET()
                            .uri(URI.create("https://rest.coinapi.io/v1/exchangerate/" + currency1 + "/" + currency2 + "/history?period_id=1HRS&time_start=" + LocalDate.now().minusDays(1) + "T00:00:00&time_end=" + LocalDate.now() + "T00:00:00"))
                            .headers("X-CoinAPI-Key", apiKey, "Accept", "application/json")         //Schlüssel in einem File klatschen und User cock blocken amena koi fick
                            .build();
                    System.out.println(LocalDate.now());
                    break;
                    //response the rate from the past 7 days (in 12h steps)
                case "1 Week":
                    request = HttpRequest.newBuilder()
                            .GET()
                            .uri(URI.create("https://rest.coinapi.io/v1/exchangerate/" + currency1 + "/" + currency2 + "/history?period_id=1DAY&time_start=" + LocalDate.now().minusDays(7) + "T00:00:00&time_end=" + LocalDate.now() + "T00:00:00"))
                            .headers("X-CoinAPI-Key", apiKey, "Accept", "application/json")         //Schlüssel in einem File klatschen und User cock blocken amena koi fick
                            .build();
                    System.out.println(LocalDate.now());
                    break;
                    //response the rate from the past 30 days (in 1 day steps)
                case "1 Month":
                    request = HttpRequest.newBuilder()
                            .GET()
                            .uri(URI.create("https://rest.coinapi.io/v1/exchangerate/" + currency1 + "/" + currency2 + "/history?period_id=1DAY&time_start=" + LocalDate.now().minusDays(30) + "T00:00:00&time_end=" + LocalDate.now() + "T00:00:00"))
                            .headers("X-CoinAPI-Key", apiKey, "Accept", "application/json")         //Schlüssel in einem File klatschen und User cock blocken amena koi fick
                            .build();
                    System.out.println(LocalDate.now());
                    break;
                    //response the rate from the past 365 days (in 30 day steps)
                case "1 Year":
                    request = HttpRequest.newBuilder()
                            .GET()
                            .uri(URI.create("https://rest.coinapi.io/v1/exchangerate/" + currency1 + "/" + currency2 + "/history?period_id=10DAY&time_start=" + LocalDate.now().minusDays(365) + "T00:00:00&time_end=" + LocalDate.now() + "T00:00:00"))
                            .headers("X-CoinAPI-Key", apiKey, "Accept", "application/json")         //Schlüssel in einem File klatschen und User cock blocken amena koi fick
                            .build();
                    System.out.println(LocalDate.now());
                default:
                    System.out.println("Coming soon in the alpha");
                    break;

            }

            //The content is saved it response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            //Outputs the status code
            System.out.println(response.statusCode());

            //Outputs the content of the request
            System.out.println(response.body());

            response.body();
            //Calls parseJSON
            List list = JSONParse.parseJSON(response.body(), time);
            return list;


        } catch (IOException iOE) {
            System.out.println("ERROR: " + iOE.toString());

        } catch (InterruptedException iE) {
            System.out.println("ERROR: " + iE.toString());
        }
        return null;
    }

    /**
     *
     * @param iconSize Size of Icon
     */

    //Gets the icons of the currencies
    public String getIcon(int iconSize){
        try {
            HttpRequest request;
            request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://rest.coinapi.io/v1/assets/icons/" + iconSize + "/filter_asset_id={BTC}"))
                    .headers("X-CoinAPI-Key", apiKey, "Accept", "application/json")
                    .build();

            //The content is saved it response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            //Outputs the status code
            System.out.println(response.statusCode());

            //Outputs the content of the request
            System.out.println(response.body());

            //Calls parseJSON
            //JSONParse.parseJSON(response.body());     needs a Second method

            //Wenn es zu einem ERROR kommt catch
        }catch(IOException iOE){
            System.out.println("ERROR: " + iOE.toString());

        }catch(InterruptedException iE){
            System.out.println("ERROR: " + iE.toString());
        }
        return null;
    }

    //Gets a list of all currencies
    public void getAllAssets(){
        try {
            HttpRequest request;
            request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://rest.coinapi.io/v1/assets"))
                    .headers("X-CoinAPI-Key", apiKey, "Accept", "application/json")
                    .build();

            //The content is saved it response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            //Outputs the status code
            System.out.println(response.statusCode());

            //Outputs the content of the request
            //System.out.println(response.body());

            JSONParse.parseJSONAsset(response.body());

            //Wenn es zu einem ERROR kommt catch
        }catch(IOException iOE){
            System.out.println("ERROR: " + iOE.toString());

        }catch(InterruptedException iE){
            System.out.println("ERROR: " + iE.toString());
        }
    }
}
