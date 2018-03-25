package ru.my.tasks.concurrency.synqq;


import java.util.Random;

public class Writer implements Runnable {
    //should be inject =)
    EventDao eventDao = new EventDao();
    private static final Random random = new Random();


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            int eventId = random.nextInt(BaseSettings.EVENT_NUMBER);
            Event newEvent = new Event(eventId, random.nextInt(BaseSettings.TIME_RANGE), BaseSettings.generateAttendees());
            eventDao.addEvent(newEvent);

            System.out.println(Thread.currentThread().getName() + " " + newEvent.toString());
        }
    }
}
