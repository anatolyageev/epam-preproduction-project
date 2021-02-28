package com.epam.anatolii.ageev.task01;

import com.epam.anatolii.ageev.eshop.utils.ReflectionUtils;
import com.epam.anatolii.ageev.task01.common_collection.PrimeNumbers;
import com.epam.anatolii.ageev.task01.separate_collection.PrimeNumbersToInnerArray;

/**
 *  Class for run all type of searching Prime Numbers.
 */
public class PrimeNumbersApp {
    private int diapasonFrom;
    private int diapasonTo;
    private int noOfStreams;

    private PrimeNumbersApp() {

    }

    public static void main(String[] args) {
        PrimeNumbersApp app = new PrimeNumbersApp();
        app.appRunner();

    }

    private void appRunner() {
        System.out.println("Please enter diapason from 1 to 10,000: ");

        System.out.println("Please input start of diapason: ");
        diapasonFrom = ReflectionUtils.inputInteger();
        System.out.println("Please input end of diapason: ");
        diapasonTo = ReflectionUtils.inputInteger();
        System.out.println("Please input number of streams: ");
        noOfStreams = ReflectionUtils.inputInteger();

        PrimeNumbers primeNumbers = new PrimeNumbers(diapasonFrom, diapasonTo, noOfStreams);
        PrimeNumbersToInnerArray primeNumbersToInnerArray = new PrimeNumbersToInnerArray(diapasonFrom, diapasonTo, noOfStreams);
        primeNumbers.appRunner();
        primeNumbers.appRunnerExecutors();
        primeNumbersToInnerArray.appRunner();

    }
}
