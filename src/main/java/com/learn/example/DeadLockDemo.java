package com.learn.example;

class DeadLock {
    private int counter = 0;
    private Object lock1 = new Object();
    private Object lock2 = new Object();
    Runnable incrementer = new Runnable() {
        @Override
        public void run() {
            try {
                for(int i = 0;i < 100;i++) {
                    incrementCounter();
                    System.out.println("Incrementing " + i);
                }
            }
            catch (InterruptedException ex){}
        }
    };
    Runnable decrementer = new Runnable() {
        @Override
        public void run() {
            try {
                for(int i = 0;i < 100;i++) {
                    decrementCounter();
                    System.out.println("Incrementing " + i);
                }
            }
            catch (InterruptedException ex) {}
        }
    };

    void incrementCounter() throws InterruptedException {
        synchronized (lock1) {
            System.out.println(Thread.currentThread() + " : Acquired lock1");
            Thread.sleep(100);
            synchronized (lock2) {
                counter++;
            }
        }
    }
    void decrementCounter() throws InterruptedException {
        synchronized (lock2) {
            System.out.println(Thread.currentThread() + " : Acquired lock2");
            Thread.sleep(100);
            synchronized (lock1) {
                counter--;
            }
        }
    }
    public void runTest() throws InterruptedException {

        Thread thread1 = new Thread(incrementer);
        Thread thread2 = new Thread(decrementer);

        thread1.start();
        // sleep to make sure thread 1 gets a chance to acquire lock1
        Thread.sleep(100);
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Done : " + counter);
    }
}
public class DeadLockDemo {
    public static void main(String[] args) throws InterruptedException{
        DeadLock deadLock = new DeadLock();
        deadLock.runTest();
    }
}
