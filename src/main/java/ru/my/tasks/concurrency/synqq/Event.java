package ru.my.tasks.concurrency.synqq;


import java.util.HashSet;
import java.util.Set;

public final class Event {

    private final int id;
    private final int startTime;
    private final Set<Integer> attendees; //condition on 5 elements

    public Event(int id, int startTime, Set<Integer> attendees) {
        this.id = id;
        this.startTime = startTime;
        this.attendees = new HashSet<>();
        this.attendees.addAll(attendees);
    }

    public int getId() {
        return id;
    }

    public int getStartTime() {
        return startTime;
    }

    public Set<Integer> getAttendees() {
        Set<Integer> attendeesCopy = new HashSet<>();
        attendeesCopy.addAll(attendees);
        return attendeesCopy;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", attendees=" + attendees +
                '}';
    }
}
