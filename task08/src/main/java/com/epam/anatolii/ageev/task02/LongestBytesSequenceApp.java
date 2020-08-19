package com.epam.anatolii.ageev.task02;

import java.io.IOException;

/**
 * Class for running threads.
 */
public class LongestBytesSequenceApp {
    public static void main(String[] args) {
        LongestBytesSequence longestBytesSequence = new LongestBytesSequence();

        Thread thread1 = new Thread(longestBytesSequence::produce);

        Thread thread2 = new Thread(longestBytesSequence::consume);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}