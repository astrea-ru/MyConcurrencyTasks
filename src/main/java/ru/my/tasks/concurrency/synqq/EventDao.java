package ru.my.tasks.concurrency.synqq;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class EventDao {

    private static final ConcurrentHashMap<Integer, Event> eventStorage = new ConcurrentHashMap<>();

    public void addEvent(Event event) {
        eventStorage.put(event.getId(), event);
    }

    public Event getEvent(int id) {
        return eventStorage.get(id);
    }

    public List<Event> subSequence(Integer t1, Integer t2, Set<Integer> attendees) {
        List<Event> existing = new ArrayList<>(eventStorage.values());

        return existing.stream().filter(event -> event.getStartTime() >= t1 && event.getStartTime() <= t2)
                .filter(event -> attendees.containsAll(event.getAttendees()))
                .collect(Collectors.toList());
    }
}
