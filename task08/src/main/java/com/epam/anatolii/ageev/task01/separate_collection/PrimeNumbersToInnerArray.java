package com.epam.anatolii.ageev.task01.separate_collection;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 *  Class for find Prime Numbers using  collections for each thread.
 */
public class PrimeNumbersToInnerArray {
    private int diapasonFrom;
    private int diapasonTo;
    private int noOfStreams;
    private Set<Integer> primeNumbers;
    private List<Thread> threadList;
    private List<Future> futureList;

    public PrimeNumbersToInnerArray(int diapasonFrom, int diapasonTo, int noOfStreams){
        this.diapasonFrom = diapasonFrom;
        this.diapasonTo = diapasonTo;
        this.noOfStreams = noOfStreams;
        primeNumbers  = new TreeSet<>();
    }

    private Callable<List<Integer>> newThread(int from, int to) {
        return () -> {
            List<Integer> integers = new ArrayList<>();
            for (int i = from; i <= to; i++) {
                if(i==1){continue;}
                boolean flagIsPrime = true;
                for (int simpl = 2; simpl <= i / 2; simpl++) {
                    if (i % simpl == 0) {
                        flagIsPrime = false;
                        break;
                    }
                }
                if (flagIsPrime) {
                    integers.add(i);
                }
            }
            return integers;
        };
    }

    /**
     *  Method to run threads.
     */
    public void appRunner() {
        getTaredPool(noOfStreams);
        long before = System.nanoTime();
        for (Thread thread1 : threadList) {
            thread1.start();
        }

        for (Future f : futureList) {
            try {
               primeNumbers.addAll((List<Integer>) f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        long after = System.nanoTime();
        System.out.println("Collection for each threads: " + (after - before) + " ns");
        System.out.println(primeNumbers); // if needed show result

    }

    private void getTaredPool(int noOfStreams) {
        threadList = new ArrayList<>();
        futureList = new ArrayList<>();
        int delta = (diapasonTo - diapasonFrom) / noOfStreams;
        int tempDiapasonFrom = diapasonFrom;
        int tempDiapasonTo = delta;
        for (int i = 0; i < noOfStreams; i++) {
            if (i < (noOfStreams - 1)) {
                FutureTask<List<Integer>> future = new FutureTask(newThread(tempDiapasonFrom, tempDiapasonTo));
                threadList.add(new Thread(future));
                futureList.add(future);
            } else {
                FutureTask<List<Integer>> future = new FutureTask(newThread(tempDiapasonFrom, diapasonTo));
                threadList.add(new Thread(future));
                futureList.add(future);
            }
            tempDiapasonFrom += delta;
            tempDiapasonTo += delta;
        }
    }
}
