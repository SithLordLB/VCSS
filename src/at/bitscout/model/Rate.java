package at.bitscout.model;
/*
    Author: LB
    Created on: 26.04.2021
    Changed on: 20.05.2021
    Changed from: LB    added some JSON Property stuff
    Description: Creates courses for the currencies
 */
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

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

    //Constructor
    public Rate(String asset_id_base, String asset_id_quote, double rate, Date time) {
        this.asset_id_base = asset_id_base;
        this.asset_id_quote = asset_id_quote;
        this.rate = rate;
        this.time = time;
    }

    //Constructor for the left side thing
    public Rate(String asset_id, double priceInUSD) {
        this.asset_id = asset_id;
        this.priceInUSD = priceInUSD;
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
