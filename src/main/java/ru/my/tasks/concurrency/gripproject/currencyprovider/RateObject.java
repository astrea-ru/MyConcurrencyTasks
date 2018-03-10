package ru.my.tasks.concurrency.gripproject.currencyprovider;

import java.text.SimpleDateFormat;
import java.util.Date;

//Class MUST BE immutable
public final class RateObject {
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private final String currency;
    private final Date date;
    private final double rate;

    public RateObject(String currency, Date date, double rate) {
        this.currency = currency;
        this.date = date;
        this.rate = rate;
    }

    public String getCurrency() {
        return currency;
    }

    //Will return a copy, because Date is not a immutable class
    public Date getDate() {
        return new Date(date.getTime());
    }

    public double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return "RateObject{" +
                "currency='" + currency + '\'' +
                ", date=" + format.format(date) +
                ", rate=" + rate +
                '}';
    }
}
