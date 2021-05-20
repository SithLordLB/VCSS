package sample;
/*
    Author: LB
    Created on: 26.04.2021
    Changed on: 26.04.2021
    Changed from:
    Description: Currency class, it lets create for every currency a object, will be put in a list
 */
public class Currency {
    private String name;
    private String isoCode;

    public Currency(){

    }

    public Currency(String name, String isoCode) {
        this.name = name;
        this.isoCode = isoCode;
    }

    //Getter & Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }
}
