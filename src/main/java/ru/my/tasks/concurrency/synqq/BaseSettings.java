package ru.my.tasks.concurrency.synqq;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BaseSettings {

    public static int EVENT_NUMBER = 10000;
    public static int WRITER_NUMBERS = 10;
    public static int READER_NUMBERS = 10;
    public static int TIME_RANGE = 100000;
    public static int MAX_ATTENDEES = 5;
    public static int ATTENDEES_ID_RANGE = 10;

    private static final Random random = new Random();

    public static Set<Integer> generateAttendees(){
        int attendeesNum = random.nextInt(BaseSettings.MAX_ATTENDEES)+1;
        Set<Integer> attendees = new HashSet<>();
        do{
            attendees.add(random.nextInt(BaseSettings.ATTENDEES_ID_RANGE)+1);
        } while (attendeesNum != attendees.size());
        return attendees;
    }
}
