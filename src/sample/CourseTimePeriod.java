package sample;
/*
    Author: LB
    Created on: 08.06.2021
    Changed on: 08.06.2021
    Changed from: LB
    Description: Using for the timeDataSeries of coinapi to put the json response (which is an array) in a List of CourseTimePeriode objects
                 more about in https://docs.coinapi.io/?java#timeseries-data-get
 */
import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseTimePeriod {

    @JsonProperty("time_period_start")
    private String time_period_start;

    @JsonProperty("time_period_end")
    private String time_period_end;

    @JsonProperty("rate_open")
    private String rate_open;

    @JsonProperty("close")
    private String close;

    //Standard construktor
    public CourseTimePeriod(){

    }

    //Construktor
    public CourseTimePeriod(String time_period_start, String time_period_end, String rate_open, String close) {
        this.time_period_start = time_period_start;
        this.time_period_end = time_period_end;
        this.rate_open = rate_open;
        this.close = close;
    }

    //Getter & Setter
    public String getTime_period_start() {
        return time_period_start;
    }

    public void setTime_period_start(String time_period_start) {
        this.time_period_start = time_period_start;
    }

    public String getTime_period_end() {
        return time_period_end;
    }

    public void setTime_periodeend(String time_period_end) {
        this.time_period_end = time_period_end;
    }

    public String getRate_open() {
        return rate_open;
    }

    public void setRate_open(String rate_open) {
        this.rate_open = rate_open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }
}
