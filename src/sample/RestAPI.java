package sample;
/*
    Author: LB
    Created on: 03.05.2021
    Changed on: 13.05.2021
    Changed from: LB
    Description: This class provides the information for the currencies, exchanges,......
 */
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestAPI {
    private String apiString;
    private Course course;
    static final HttpClient httpClient
            = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .build();

    //Gets the course of 2 given currencies
    public Course getAPICourse(String currency1, String currency2){
        try {
            HttpRequest request;
                request = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create("https://rest.coinapi.io/v1/exchangerate/" + currency1 + "/" + currency2))
                        .headers("X-CoinAPI-Key", "60D204E3-25E1-4A7C-8385-1A4847F6C8BB", "Accept", "application/json")         //Schlüssel in einem File klatschen und User cock blocken amena koi fick
                        .build();

            //The content is saved it response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            //Outputs the status code
            System.out.println(response.statusCode());

            //Outputs the content of the request
            System.out.println(response.body());

            apiString = response.body();
            System.out.println(apiString);
            //Calls parseJSON
            course = JSONParse.parseJSON(response.body());


        }catch(IOException iOE){
            System.out.println("ERROR: " + iOE.toString());

        }catch(InterruptedException iE){
            System.out.println("ERROR: " + iE.toString());
        }

        return course;
    }

    //Gets the icons of the currencies
    private String getIcon(int iconSize){
        try {
            HttpRequest request;
            request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://rest.coinapi.io/v1/assets/icons/" + iconSize))
                    .headers("X-CoinAPI-Key", "60D204E3-25E1-4A7C-8385-1A4847F6C8BB", "Accept", "application/json")
                    .build();

            //The content is saved it response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            //Outputs the status code
            System.out.println(response.statusCode());

            //Outputs the content of the request
            System.out.println(response.body());

            apiString = response.body();
            System.out.println(apiString);
            //Calls parseJSON
            //JSONParse.parseJSON(response.body());     needs a Second method

            //Wenn es zu einem ERROR kommt catch
        }catch(IOException iOE){
            System.out.println("ERROR: " + iOE.toString());

        }catch(InterruptedException iE){
            System.out.println("ERROR: " + iE.toString());
        }
        return apiString;
    }
}
