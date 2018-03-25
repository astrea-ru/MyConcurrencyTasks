package ru.my.tasks.concurrency.synqq;


import java.util.ArrayList;
import java.util.List;

public class MainApp {

    //TODO: make work with Database (Hibernate) and save attendees without duplicates!
    //TODO: Calculate and show audit information
    //TODO: Excluding deadlocks  
    //TODO: make refactoring for common parts of functionality
    //TODO: think about more good variant for thread starting

    public static void main(String... args) throws InterruptedException {
        //Find better decision for start threads and interrupt them
        List<Thread> writers = new ArrayList<>();
        List<Thread> readers = new ArrayList<>();

        for (int i = 0; i < BaseSettings.WRITER_NUMBERS; i++) {
            writers.add(new Thread(new Writer(), "Writer #" + (i + 1)));
        }
        for (int i = 0; i < BaseSettings.READER_NUMBERS; i++) {
            readers.add(new Thread(new Reader(), "Reader #" + (i + 1)));
        }

        writers.forEach(Thread::start);
        readers.forEach(Thread::start);

        Thread.sleep(1000);

        writers.forEach(Thread::interrupt);
        readers.forEach(Thread::interrupt);
    }

}
