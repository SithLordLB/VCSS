package sample;
/*
    Author: LB
    Created on: 03.05.2021
    Changed on: 13.05.2021
    Changed from: LB
    Description: This class provides the information for the currencies, exchanges,......
 */

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;

public class RestAPI {
    private Course course;
    static final HttpClient httpClient
            = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .build();
    HttpRequest request;
    private final String apiKey = "803FBDC3-180C-47D4-B4EB-669EFB2B3E7A";
    //Date varibales to get the course of the day


    //Gets the course of 2 given currencies
    public Course getAPICourse(String currency1, String currency2) {
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
            course = JSONParse.parseJSON(response.body());


        } catch (IOException iOE) {
            System.out.println("ERROR: " + iOE.toString());

        } catch (InterruptedException iE) {
            System.out.println("ERROR: " + iE.toString());
        }

        return course;
    }

    //Gets the exchange rate in a time periode
    public List getAPICourse(String currency1, String currency2, String time) {
        try {
            switch (time) {
                case "1D":
                    request = HttpRequest.newBuilder()
                            .GET()
                            .uri(URI.create("https://rest.coinapi.io/v1/exchangerate/" + currency1 + "/" + currency2 + "/history?period_id=1HRS&time_start=" + LocalDate.now().minusDays(1) + "T00:00:00&time_end=" + LocalDate.now() + "T00:00:00"))
                            .headers("X-CoinAPI-Key", apiKey, "Accept", "application/json")         //Schlüssel in einem File klatschen und User cock blocken amena koi fick
                            .build();
                    System.out.println(LocalDate.now());
                    break;
                default:
                    System.out.println("Coming soon in se alpha");
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
            List list = JSONParse.parseJSON(response.body(), "1D");
            return list;


        } catch (IOException iOE) {
            System.out.println("ERROR: " + iOE.toString());

        } catch (InterruptedException iE) {
            System.out.println("ERROR: " + iE.toString());
        }
        return null;
    }

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
