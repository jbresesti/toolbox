package com.wpers.snippet;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Java program to demonstrate how to print and get stack trace for current* thread in Java.
 * Stack trace are useful information while debugging or * troubleshooting any issue.
 */
public class StackTraceExample {

    private static final Logger logger = Logger.getLogger(StackTraceExample.class.getName());

    public static void main(String args[]) {

        logger.log(Level.SEVERE, "StackTraceExample");

        //calling a method to print stack trace further down
        first();
    }

    public static void first() {
        second();
    }

    private static void second() {
        third();
    }

    private static void third() {
        //If you want to print stack trace on console than use dumpStack() method
        System.err.println("Stack trace of current thread using dumpStack() method");
        Thread.currentThread().dumpStack();

        //This is another way to print stack trace from current method
        System.err.println("Printing stack trace using printStackTrace() method of Throwable ");
        new Throwable().printStackTrace();

        //If you want stack trace as StackTraceElement in program itself than
        // use getStackTrace() method of Thread class
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        // Once you get StackTraceElement you can also print it to console
        System.err.println("displaying Stack trace from StackTraceElement in Java");
        for (StackTraceElement st : stackTrace) {
            // System.err.println(st);
        }
    }
}

