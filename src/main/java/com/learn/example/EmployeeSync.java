package com.learn.example;
/*
All the three methods are synchronized on the "this" object.
If we created an object and three different threads attempted to
execute each method of the object, only one will get access, and the other two will block.
*/

public class EmployeeSync {
    private String name;
    public synchronized void setName(String name) {
        this.name = name;
    }
    public synchronized void resetName() {
        this.name = "";
    }
    public String getName() {
        synchronized (this) {
            return this.name;
        }
    }
}

/*
In that scenario, since setName and resetName would have been
synchronized on the this object only one of the two methods
could be executed concurrently. However getName would be
synchronized independently of the other two methods
and can be executed alongside one of them
 */
class EmployeeSyncModify {
    private String name;
    private Object lock = new Object();
    public synchronized void setName(String name) {
        this.name = name;
    }
    public synchronized void resetName() {
        this.name = "";
    }
    public String getName() {
        synchronized (lock) {
            return this.name;
        }
    }
}
/*
All the sections of code that you guard with synchronized
blocks on the same object can have at most one thread
executing inside of them at any given point in time.
These sections of code may belong to different methods,
classes or be spread across the code base.
 */


