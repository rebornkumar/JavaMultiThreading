package com.learn.example;
import java.lang.*;
import java.util.*;
import java.io.*;

public class DemoThread {
    public static void main(String[] args) throws InterruptedException{
        singleThread();
        multiThread();
    }
    static void singleThread() {
        Range range = new Range(1,Integer.MAX_VALUE);
        long startTime = System.currentTimeMillis();
        range.add();
        long result = range.total;
        long endTime = System.currentTimeMillis();
        System.out.println("result : " + result + " timeTaken in single thread : " + (endTime - startTime));
    }
    static void multiThread() throws InterruptedException{
        Range firstHalf = new Range(1,Integer.MAX_VALUE/2);
        Range secondHalf = new Range(Integer.MAX_VALUE/2 +1 , Integer.MAX_VALUE);
        Thread firstThread = new Thread(() -> {
            firstHalf.add();
        });
        Thread secondThread = new Thread(() -> {
            secondHalf.add();
        });
        long startTime = System.currentTimeMillis();
        firstThread.start();
        secondThread.start();
        firstThread.join();
        secondThread.join();
        long result = firstHalf.total + secondHalf.total;
        long endTime = System.currentTimeMillis();
        System.out.println("result : " + result + " timeTaken in multiple thread : " + (endTime - startTime));
    }
}
class Range {
    long start;
    long end;
    long total = 0;
    Range(long start,long end) {
        this.start = start;
        this.end = end;
    }
    public void add() {
        for(long i = start;i <= end;i++) {
            total += i;
        }
    }
}
