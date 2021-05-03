package sample;
/*
    Author: LB
    Created on: 26.04.2021
    Changed on: 26.04.2021
    Changed from:
    Description: Sub class of Currency, commercial currencies
 */
public class Fiat extends Currency {
    public Fiat(String name, String isoCode) {
        super(name, isoCode);
    }
}
