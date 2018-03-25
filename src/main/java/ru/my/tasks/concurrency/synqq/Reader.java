package ru.my.tasks.concurrency.synqq;


import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Reader implements Runnable {
    //should be inject =)
    EventDao eventDao = new EventDao();
    private static final Random random = new Random();

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            int t1 = random.nextInt(BaseSettings.TIME_RANGE);
            int t2 = random.nextInt(BaseSettings.TIME_RANGE);
            if (t1 > t2) {
                int tmp = t2;
                t2 = t1;
                t1 = tmp;
            }
            Set<Integer> attendees = BaseSettings.generateAttendees();

            List<Event> events = eventDao.subSequence(t1, t2, attendees);

            System.out.println(Thread.currentThread().getName() + " time: [" + t1 + "," + t2 + "]; attendees " + attendees + " id: " +
                    events.stream().map(Event::getId).collect(Collectors.toList()));
        }
    }
}
