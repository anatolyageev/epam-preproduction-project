package com.epam.anatolii.ageev.task02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Class to find biggest sequence using pattern producer-consume
 */
public class LongestBytesSequence {
    private final Object lock1;
    private final Object lock2;
    private int size;
    private int index;
    private boolean isCalculationFinish;
    private Scanner scanner;
    private List<Byte> result;
    private int firstIndex;
    private int secondIndex;
    private File file;

    public LongestBytesSequence() {
        lock1 = new Object();
        lock2 = new Object();
        scanner = new Scanner(System.in);
        result = new ArrayList<>();
        isCalculationFinish = true;
    }

    /**
     * Method which realizing producer for pattern producer-consume
     */
    public void consume() {
        while (true) {
            synchronized (lock1) {
                while (Objects.isNull(file)) {
                    try {
                        lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                findBiggestSequence();
                isCalculationFinish = true;
                file = null;
                lock1.notify();
            }
        }
    }

    /**
     * Method which realizing consumer for pattern producer-consume
     */

    public void produce() {
        while (true) {
            while (isCalculationFinish) {
                synchronized (lock1) {
                    if (!result.isEmpty()) {
                        System.out.println("Result : " + result + "\n" +
                                "first index: " + firstIndex + "\n" +
                                "second index: " + secondIndex);
                    }
                    while (Objects.nonNull(file)) {
                        try {
                            lock1.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    getFileName();
                    isCalculationFinish = false;
                    lock1.notifyAll();
                }
            }
            synchronized (lock2) {
                double sizeDouble = (double) size;
                int percent = (int) ((this.index / sizeDouble) * 100);
                System.out.printf("%3d%%", percent);
                System.out.print("\r");
                lock2.notifyAll();
            }
        }
    }

    /**
     * Main method which finding biggest byte sequence entrance.
     */
    private void findBiggestSequence() {
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Byte> byteArrayList = bytesToList(bytes);
        Map<List<Byte>, Integer> resultMap = new HashMap<>();
        size = byteArrayList.size();
        for (int i = size; i > 0; i--) {
            getStatus(i);
            for (int j = 0; j <= i; j++) {
                List<Byte> subListByte = byteArrayList.subList(j, i);
                if (resultMap.containsKey(subListByte) && result.size() < subListByte.size()) {
                    updateResult(subListByte, j, resultMap.get(subListByte));
                } else {
                    resultMap.put(subListByte, j);
                }
            }
        }
    }

    private void getFileName() {
        while (file == null || !file.isFile()) {
            System.out.println("Please enter file name (full path): ");
            String fileName = scanner.nextLine();
            file = new File(fileName);
        }
    }

    private List<Byte> bytesToList(byte[] bytes) {
        List<Byte> byteList = new ArrayList<>();
        for (byte item : bytes) {
            byteList.add(item);
        }
        return byteList;
    }

    private void getStatus(int index) {
        synchronized (lock2) {
            this.index = index;
            lock2.notifyAll();
        }
    }

    private void updateResult(List<Byte> result, int firstIndex, int secondIndex) {
        this.result = result;
        this.firstIndex = firstIndex;
        this.secondIndex = secondIndex;
    }
}
