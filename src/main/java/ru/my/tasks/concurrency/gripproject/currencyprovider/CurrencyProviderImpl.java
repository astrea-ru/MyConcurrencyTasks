package ru.my.tasks.concurrency.gripproject.currencyprovider;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;


public class CurrencyProviderImpl implements CurrencyProvider {

    //ConcurrentSkipListMap - like concurrent TreeMap
    //ConcurrentSkipListSet - like concurrent TreeSet
    private ConcurrentHashMap<String, ConcurrentSkipListSet<RateObject>> cash = new ConcurrentHashMap<>();

    @Override
    public void addRate(String currency, Date date, double rate) {
        if (currency == null || currency.isEmpty() || date == null) {
            throw new IllegalArgumentException("Currency or Date is null or empty!!!");
        }

        cash.putIfAbsent(currency, new ConcurrentSkipListSet<>(
                (item1, item2) -> item2.getDate().compareTo(item1.getDate())));
        cash.get(currency).add(new RateObject(currency, date, rate));
    }

    @Override
    /*date < lastKnownDate -> 0 25.01.2017 - las, date 16.11.2016*/
    /*date >= lastKnownDate -> knownValue 25.01.2017 - las, date 27.01.2017*/
    public double getLastKnownRate(String currency, Date date) {
        if (currency == null || currency.isEmpty() || date == null) {
            throw new IllegalArgumentException("Currency or Date is null or empty!!!");
        }

        if (cash.getOrDefault(currency, null) == null) {
            return 0;
        }
        RateObject rateObject = cash.get(currency).higher(new RateObject(currency, date, 0));
        return rateObject == null ? 0 : rateObject.getRate();
    }

}


