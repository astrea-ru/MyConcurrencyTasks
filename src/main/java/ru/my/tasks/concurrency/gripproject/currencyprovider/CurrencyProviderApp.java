package ru.my.tasks.concurrency.gripproject.currencyprovider;


import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static ru.my.tasks.concurrency.gripproject.currencyprovider.RateObject.DATE_FORMAT;

public class CurrencyProviderApp {

    private static final CurrencyProvider currencyProvider = new CurrencyProviderImpl();
    private static final List<String> currencies = Arrays.asList("USD", "EUR", "AUD", "RUB");
    private static final Random random = new Random();


    private class Provider implements Runnable {

        @Override
        public void run() {
            RateObject o = new RateObject(
                        currencies.get(random.nextInt(4)),
                        new Date(Math.abs(System.currentTimeMillis() - random.nextInt(100000))),
                        random.nextInt(4) + random.nextDouble());
            currencyProvider.addRate(o.getCurrency(), o.getDate(), o.getRate());
            System.out.println("Added "+o.toString());
        }
    }

    private class Consumer implements Runnable {

        @Override
        public void run() {
            String currency = currencies.get(random.nextInt(4));
            Date date = Date.from(Instant.now());
            double rate = currencyProvider.getLastKnownRate(currency, date);
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            System.out.println("-----------------------------------> Last rate for "+currency+" on "+ format.format(date) + " : " + rate);
        }
    }

    public static void main(String ...arg) throws InterruptedException {
        new CurrencyProviderApp().start();
    }

    private void start() throws InterruptedException {
        ScheduledExecutorService providerService = Executors.newScheduledThreadPool(1);
        providerService.scheduleAtFixedRate(new Provider(), 0, 1, TimeUnit.SECONDS);
        ScheduledExecutorService consumerService = Executors.newScheduledThreadPool(1);
        consumerService.scheduleAtFixedRate(new Consumer(), 0, 2, TimeUnit.SECONDS);

        Thread.sleep(10000);

        providerService.shutdownNow();
        consumerService.shutdownNow();
    }
}
