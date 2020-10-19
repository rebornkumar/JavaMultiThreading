package com.learn.example;
import java.util.*;
import java.io.*;
import java.lang.*;

class Example {
    Boolean flag  = new Boolean(true);
    public void example() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (flag) {
                    while(flag) {
                        try {
                            System.out.println("First thread about to sleep");
                            Thread.sleep(5000);
                            System.out.println("Woke up and about to invoke wait()");
                            flag.wait();
                        } catch (InterruptedException e) {

                        }
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                flag = false;
                System.out.println("Boolean assignment done.");
            }
        });
        t1.start();
        Thread.sleep(1000);
        t2.start();
        t1.join();
        t2.join();
    }
    public static void runExample() throws InterruptedException {
        Example incorrectSynchronization = new Example();
        incorrectSynchronization.example();
    }
}
public class IncorrectSynchronization {
    public static void main(String[] args) throws InterruptedException{
        Example.runExample();
    }
}
