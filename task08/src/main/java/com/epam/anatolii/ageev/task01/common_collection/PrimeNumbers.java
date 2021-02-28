package com.epam.anatolii.ageev.task01.common_collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Class for find Prime Numbers using one collection for store result.
 */
public class PrimeNumbers {
    private List<Integer> primeNumbers;
    private final Object lock = new Object();
    private List<Thread> threadList;
    private int diapasonFrom;
    private int diapasonTo;
    private int noOfStreams;

    public PrimeNumbers(int diapasonFrom, int diapasonTo, int noOfStreams) {
        this.diapasonFrom = diapasonFrom;
        this.diapasonTo = diapasonTo;
        this.noOfStreams = noOfStreams;
        primeNumbers = new ArrayList<>();
    }

    private Thread newThread(int from, int to) {
        return new Thread(() -> {
            for (int i = from; i <= to; i++) {
                if (i == 1) {
                    continue;
                }
                boolean flagIsPrime = true;
                for (int simpl = 2; simpl <= i / 2; simpl++) {
                    if (i % simpl == 0) {
                        flagIsPrime = false;
                        break;
                    }
                }
                if (flagIsPrime) {
                    addPrimeToList(i);
                }
            }
        });
    }


    /**
     * Method to run threads with Executors.
     */
    public void appRunnerExecutors() {
        ExecutorService executorService = Executors.newFixedThreadPool(noOfStreams);
        primeNumbers = new ArrayList<>();
        int delta = (diapasonTo - diapasonFrom) / noOfStreams;
        int tempDiapasonFrom = diapasonFrom;
        int tempDiapasonTo = delta;
        for (int i = 0; i < noOfStreams; i++) {
            if (i < noOfStreams - 1) {
                executorService.submit(newThread(tempDiapasonFrom, tempDiapasonTo));
            } else {
                executorService.submit(newThread(tempDiapasonFrom, diapasonTo));
            }
            tempDiapasonFrom += delta;
            tempDiapasonTo += delta;
        }
        long before = System.nanoTime();
        executorService.shutdown();

        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long after = System.nanoTime();
        System.out.println("Executors takes: " + (after - before) + " ns");
        Collections.sort(primeNumbers);
        System.out.println(primeNumbers);
    }

    /**
     * Method to run threads without Executors.
     */
    public void appRunner() {
        getThreadPool(noOfStreams);

        long before = System.nanoTime();
        threadList.forEach(Thread::start);

        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long after = System.nanoTime();
        System.out.println("Threads with one collection takes: " + (after - before) + " ns");
        Collections.sort(primeNumbers);
        System.out.println(primeNumbers);

    }

    private List<Thread> getThreadPool(int noOfStreams) {
        threadList = new ArrayList<>();
        int delta = (diapasonTo - diapasonFrom) / noOfStreams;
        int tempDiapasonFrom = diapasonFrom;
        int tempDiapasonTo = delta;
        for (int i = 0; i < noOfStreams; i++) {
            if (i < noOfStreams - 1) {
                threadList.add(newThread(tempDiapasonFrom, tempDiapasonTo));
            } else {
                threadList.add(newThread(tempDiapasonFrom, diapasonTo));
            }
            tempDiapasonFrom += delta;
            tempDiapasonTo+=delta;
        }
        return threadList;
    }

    private void addPrimeToList(int prime) {
        synchronized (lock) {
            primeNumbers.add(prime);
        }
    }

}
