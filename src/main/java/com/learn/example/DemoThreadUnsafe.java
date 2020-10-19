package com.learn.example;

import java.util.Random;

public class DemoThreadUnsafe {
    // To randomly sleep our threads
    static Random random = new Random(System.currentTimeMillis());
    public static void main(String[] args) throws InterruptedException{
        ThreadUnsafeCounter threadUnsafeCounter = new ThreadUnsafeCounter();
        Thread incrementThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i < 100;i++) {
                    threadUnsafeCounter.increment();
                    try {
                        Thread.sleep(random.nextInt(10));
                    }
                    catch (InterruptedException ex) {}
                }
            }
        });
        Thread decrementThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i < 100;i++) {
                    threadUnsafeCounter.decrement();
                    try {
                        Thread.sleep(random.nextInt(10));
                    }
                    catch (InterruptedException ex) {}
                }
            }
        });
        //start both the thread
        incrementThread.start();
        decrementThread.start();
        //wait for incrementThread to complete
        incrementThread.join();
        //wait for decrementThread to complete
        decrementThread.join();

        threadUnsafeCounter.printCounterValue();
    }
}
class ThreadUnsafeCounter {
    int counter = 0;
    public void increment() {
        counter++;
    }
    public void decrement() {
        counter--;
    }
    public void printCounterValue() {
        System.out.println("counter : " + counter);
    }
}
