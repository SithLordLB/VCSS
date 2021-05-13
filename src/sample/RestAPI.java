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

public class RestAPI {
    String apiString;
    static final HttpClient httpClient
            = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .build();

    //Gets the course of 2 given currencies
    public String getAPICourse(String currency1, String currency2){
        try {
            HttpRequest request;
                request = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create("https://rest.coinapi.io/v1/exchangerate/" + currency1 + "/" + currency2))
                        .headers("X-CoinAPI-Key", "60D204E3-25E1-4A7C-8385-1A4847F6C8BB", "Accept", "application/json")         //Schlüssel in einem File klatschen und User cock blocken amena koi fick
                        .build();

            //In response wird der Inhalt gespeichert
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            //Gibt den Status vom code aus
            System.out.println(response.statusCode());
            //Gibt den Inhalt der URL aus
            System.out.println(response.body());

            apiString = response.body().substring(1);
            System.out.println(apiString);

            //Wenn es zu einem ERROR kommt catch
        }catch(IOException iOE){
            System.out.println("ERROR: " + iOE.toString());

        }catch(InterruptedException iE){
            System.out.println("ERROR: " + iE.toString());
        }
        return apiString;
    }

    //Gets the icons of the currencies
    public String getIcon(int iconSize){
        try {
            HttpRequest request;
            request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://rest.coinapi.io/v1/assets/icons/" + iconSize))
                    .headers("X-CoinAPI-Key", "60D204E3-25E1-4A7C-8385-1A4847F6C8BB", "Accept", "application/json")
                    .build();

            //In response wird der Inhalt gespeichert
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            //Gibt den Status vom code aus
            System.out.println(response.statusCode());
            //Gibt den Inhalt der URL aus
            System.out.println(response.body());

            apiString = response.body().substring(1);
            System.out.println(apiString);

            //Wenn es zu einem ERROR kommt catch
        }catch(IOException iOE){
            System.out.println("ERROR: " + iOE.toString());

        }catch(InterruptedException iE){
            System.out.println("ERROR: " + iE.toString());
        }
        return apiString;
    }
}
