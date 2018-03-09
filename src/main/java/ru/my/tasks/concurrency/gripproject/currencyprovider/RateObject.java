package ru.my.tasks.concurrency.gripproject.currencyprovider;

import java.util.Date;


public class RateObject {
    private String currency;
    private Date date;
    private double rate;

    public RateObject(String currency, Date date, double rate) {
    }

    public String getCurrency() {
        return currency;
    }

    public Date getDate() {
        return date;
    }

    public double getRate() {
        return rate;
    }
}
