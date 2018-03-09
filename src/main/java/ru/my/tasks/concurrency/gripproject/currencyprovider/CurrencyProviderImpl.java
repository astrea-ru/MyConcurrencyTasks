package ru.my.tasks.concurrency.gripproject.currencyprovider;

import java.util.Date;

/**
 * FIXME: have not done
 * FIXME: create TESTS!!!!
 */
public class CurrencyProviderImpl implements CurrencyProvider{
    @Override
    public void addRate(String currency, Date date, double rate) {

    }

    @Override
    public double getLastKnownRate(String currency, Date date) {
        return 0;
    }
//
//    ConcurrentHashMap<String, TreeMap<RateObject/*Date, Double*/>> cash = new ConcurrentHashMap<>();
//
//    @Override
//    public void addRate(String currency, Date date, double rate) {
//        if (cash.get(currency)==null){
//            cash.put(currency, new TreeSet<>((item1, item2) -> item1.getDate().compareTo(item2.getDate())));
//        }
//        cash.get(currency).(new RateObject(currency, date, rate));
//    }
//
//    @Override
//    public double getLastKnownRate(String currency, Date date) {
//        TreeSet<RateObject> currentSet = cash.get(currency);
//        if (currentSet == null || currentSet.isEmpty()){
//            return 0;
//        }
//
//        RateObject rateObject = currentSet.higher(new RateObject(currency, date, 0));
//
//        return rateObject == null ? 0 : rateObject.getRate();
//    }
}


