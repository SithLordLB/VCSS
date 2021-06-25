package at.bitscout.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/** Creates courses for the currencies
 * @author Bandalo
 * @version 1.3
 */

public class Rate {

    @JsonProperty("asset_id_base")
    private String asset_id_base;

    @JsonProperty("asset_id_quote")
    private String asset_id_quote;

    @JsonProperty("rate")
    private double rate;

    @JsonProperty("time")
    private Date time;

    //Used for the left VBox on the side
    @JsonProperty("asset_id")
    private String asset_id;


    @JsonProperty("price_usd")
    private double priceInUSD;

    //Default Constructor
    public Rate(){

    }

    /** Constructor of Rate
     * @param asset_id_base ID of asset
     * @param asset_id_quote ID of quote
     * @param rate Rate
     * @param time Timestamp of rate
     */

    public Rate(String asset_id_base, String asset_id_quote, double rate, Date time) {
        this.asset_id_base = asset_id_base;
        this.asset_id_quote = asset_id_quote;
        this.rate = rate;
        this.time = time;
    }

    //Getter and Setter
    public String getAsset_id_base() {
        return asset_id_base;
    }

    public void setAsset_id_base(String asset_id_base) {
        this.asset_id_base = asset_id_base;
    }

    public String getAsset_id_quote() {
        return asset_id_quote;
    }

    public void setAsset_id_quote(String asset_id_quote) {
        this.asset_id_quote = asset_id_quote;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(String asset_id) {
        this.asset_id = asset_id;
    }

    public double getPriceInUSD() {
        return priceInUSD;
    }

    public void setPriceInUSD(double priceInUSD) {
        this.priceInUSD = priceInUSD;
    }
}
