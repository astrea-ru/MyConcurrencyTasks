package ru.my.tasks.concurrency.waitnotify;


public class WaitNotifyForObject {

     private final Message msg = new Message("first");

    private class A implements Runnable {

        public void run() {
            synchronized (msg) {
                System.out.println("<A> --------------> " + msg.getText());
                try {
                    msg.setText("This is trash");
                    System.out.println("<A> --------------> " + msg.getText());
                    msg.wait();
                    msg.setText("This is A");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("<A> --------------> " + msg.getText());
                }
            }
        }
    }

    private class B implements Runnable {

        public void run() {
            synchronized (msg) {
                System.out.println("<B> --------------> " + msg.getText());
                msg.setText("This is B");
                System.out.println("<B> --------------> " + msg.getText());
                msg.notifyAll();
            }
        }
    }

    public static void main(String ...args) throws InterruptedException {
        System.out.println("Hi");
        WaitNotifyForObject app = new WaitNotifyForObject();
        app.start();
    }

    private void start() throws InterruptedException {
        Thread a = new Thread(new A());
        Thread b = new Thread(new B());
        //Смена последовательности уводит в блокировку)))))
        a.start();
        //Thread.sleep(2000);
        b.start();
    }

}
