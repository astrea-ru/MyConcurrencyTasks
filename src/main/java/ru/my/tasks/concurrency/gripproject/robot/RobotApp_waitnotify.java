package ru.my.tasks.concurrency.gripproject.robot;


//Up left, Up right hands step by step
public class RobotApp_waitnotify {

    private static final Object lock = new Object();

    private class RightHand implements Runnable {

        @Override
        public void run() {
            try {
                synchronized (lock) {
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println("RIGHT");
                        lock.notifyAll();
                        lock.wait();
                    }
                }
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }

    private class LeftHand implements Runnable {

        @Override
        public void run() {
            try {
                synchronized (lock) {
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println("LEFT");
                        lock.notifyAll();
                        lock.wait();
                    }
                }
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String ...args) throws InterruptedException {
        new RobotApp_waitnotify().start();
    }

    private void start() throws InterruptedException {
        Thread right = new Thread(new RightHand());
        Thread left = new Thread(new LeftHand());
        left.start();
        right.start();
        Thread.sleep(5);
        right.interrupt();
        left.interrupt();
    }
}
