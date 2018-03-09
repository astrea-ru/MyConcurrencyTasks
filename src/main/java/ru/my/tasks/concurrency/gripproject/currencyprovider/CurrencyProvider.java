package ru.my.tasks.concurrency.gripproject.currencyprovider;

import java.util.Date;

public interface CurrencyProvider {
    void addRate(String currency, Date date, double rate); //"USD", 25.01.2017
    double getLastKnownRate(String currency, Date date);
    /*date < lastKnownDate -> 0 25.01.2017 - las, date 16.11.2016*/
    /*date >= lastKnownDate -> knownValue 25.01.2017 - las, date 27.01.2017*/
}