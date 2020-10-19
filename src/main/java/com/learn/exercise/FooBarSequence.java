package com.learn.exercise;

import java.util.*;
import java.lang.*;
import java.io.*;

class PrintFooBar {
    int n;
    int flag;
    PrintFooBar(int n,int flag) {
        this.n = n;
        this.flag = flag;
    }
    public void printFoo() {
        for (int i = 1;i <= n;  i++){
            synchronized (this) {
                if(flag == 1) {
                    try{
                        this.wait();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Foo");
                flag = 1;
                this.notifyAll();
            }
        }
    }
    public void printBar() {
        for (int i = 1; i <= n; i++) {
            synchronized (this) {
                if(flag == 0) {
                    try{
                        this.wait();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Bar");
                flag = 0;
                this.notifyAll();
            }
        }
    }
}
class FooBarThread extends Thread{
    PrintFooBar printFooBar;
    String threadName;
    FooBarThread(PrintFooBar printFooBar,String threadName) {
        this.printFooBar = printFooBar;
        this.threadName = threadName;
    }
    public void run() {
        if("foo".equalsIgnoreCase(this.threadName)) {
            printFooBar.printFoo();
        }
        if("bar".equalsIgnoreCase(this.threadName)) {
            printFooBar.printBar();
        }
    }
}
public class FooBarSequence {
    public static void main(String[] args) throws InterruptedException{
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        PrintFooBar commonResource = new PrintFooBar(n,0);
        Thread fooThread = new FooBarThread(commonResource,"foo");
        Thread barThread = new FooBarThread(commonResource,"bar");
        fooThread.start();
        barThread.start();

    }
}
