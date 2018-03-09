package ru.my.tasks.concurrency.gripproject.robot;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Up left, Up right hands step by step
public class RobotApp_conditions {

    private static final Lock lock = new ReentrantLock();
    private static final Condition rightHandUpCondition = lock.newCondition();
    private static final Condition leftHandUpCondition = lock.newCondition();

    private class RightHand implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("RIGHT");
                    leftHandUpCondition.signal();
                    rightHandUpCondition.await();
                }
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
            finally {
                lock.unlock();
            }
        }
    }

    private class LeftHand implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("LEFT");
                    rightHandUpCondition.signal();
                    leftHandUpCondition.await();
                }
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String ...args) throws InterruptedException {
        new RobotApp_conditions().start();
    }

    private void start() throws InterruptedException {
        Thread right = new Thread(new RightHand());
        Thread left = new Thread(new LeftHand());
        left.start();
        right.start();
        Thread.sleep(6);
        right.interrupt();
        left.interrupt();
    }
}
