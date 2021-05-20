package sample;
/*
    Author: LB
    Created on: 26.04.2021
    Changed on: 20.05.2021
    Changed from: LB    added some JSON Property stuff
    Description: Creates courses for the currencies
 */
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Course {

    @JsonProperty("asset_id_base")
    private String asset_id_base;

    @JsonProperty("asset_id_quote")
    private String asset_id_quote;

    @JsonProperty("rate")
    private double rate;

    @JsonProperty("time")
    private Date time;

    //Default Constructor
    public Course(){

    }

    //Constructor
    public Course(String asset_id_base, String asset_id_quote, double rate, Date time) {
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
}
