package at.bitscout.model;

import at.bitscout.helper.ACLogger;
import at.bitscout.helper.JSONParse;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;

/** This class provides the information for the currencies, exchanges,...
 * @author Bandalo
 * @version 1.3
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


    /** Gets with the filter some Crypto to create a List with them
     * @return returns list of rates
     */
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
            //logs the status code of the string
            switch (response.statusCode()){
                case 400:
                    ACLogger.writeCorrespondence("API ERROR","There is something wrong with your request");
                    break;
                case 401:
                    ACLogger.writeCorrespondence("API ERROR","Unauthorized -- Your API key is wrong");
                    break;
                case 403:
                    ACLogger.writeCorrespondence("API ERROR","Forbidden -- Your API key doesnt't have enough privileges to access this resource");
                    break;
                case 429:
                    ACLogger.writeCorrespondence("API ERROR","Too many requests -- You have exceeded your API key rate limits");
                    break;
                case 550:
                    ACLogger.writeCorrespondence("API ERROR","No data -- You requested specific single item that we don't have at this moment.");
                    break;
                case 200:
                    ACLogger.writeCorrespondence("API SUCESS","The Request has been authorized");
                    break;
            }


            //Outputs the content of the request
            //System.out.println(response.body());

            //Calls parseJSON
            rateList = JSONParse.parseJSONSide(response.body());


        } catch (IOException iOE) {
            ACLogger.writeCorrespondence("ERROR","IOException in RestAPI getApiCourse (Key)");

        } catch (InterruptedException iE) {
            ACLogger.writeCorrespondence("ERROR","Interrupt Exeption in RestAPI getApiCourse");
        }

        return rateList;
    }

    /** Gets the course of 2 given currencies
     * @param currency1 Variable of first currency
     * @param currency2 Variable of second currency
     * @return Conversion rate gets returned
     */

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
            ACLogger.writeCorrespondence("ERROR","IOE Exeption in RestAPI getAPICourse 1");

        } catch (InterruptedException iE) {
            ACLogger.writeCorrespondence("ERROR","Interrupt Exeption in RestAPI getAPICourse 1");
        }

        return rate;
    }

    /** Gets the exchange rate in a time periode
     * @param currency1 Variable of first currency
     * @param currency2 Variable of second currency
     * @param time Time period
     * @return Returns list of time periods
     */
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
            if (response.body()!=""){
                ACLogger.writeCorrespondence("FETCHING","It has Fetched the Course Sucessfully");
            }

            //Outputs the status code
            System.out.println(response.statusCode());

            //Outputs the content of the request
            System.out.println(response.body());

            response.body();
            //Calls parseJSON
            List list = JSONParse.parseJSON(response.body(), time);
            return list;


        } catch (IOException iOE) {
            ACLogger.writeCorrespondence("ERROR","IOE Exeption in Restapi geAPICourse 2 (Fehler in API)");

        } catch (InterruptedException iE) {
            ACLogger.writeCorrespondence("ERROR","InterruptedException in Restapi getAPICourse 2 (Fehler in API)");
        }
        return null;
    }

    /** Gets the icons of the currencies
     * @param iconSize Size of Icon
     */

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
            ACLogger.writeCorrespondence("ERROR","IOE Exeption in RestAPI getIcon");

        }catch(InterruptedException iE){
            ACLogger.writeCorrespondence("ERROR","Interrupt Exeption in RestAPI getIcon");
        }
        return null;
    }


    /** Gets a list of all currencies*/
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
            ACLogger.writeCorrespondence("ERROR","IOE Exeption in RestAPI getAllAssets");

        }catch(InterruptedException iE){
            ACLogger.writeCorrespondence("ERROR","Interrupt Exeption Exeption in RestAPI getAllAssets");
        }
    }
}
