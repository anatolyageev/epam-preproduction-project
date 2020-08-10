package com.epam.anatolii.ageev.task01;

public class Tester {
    public static void main(String[] args) {
        TextFileViewer viewer = new TextFileViewer("task05/task01ForTest.txt");
        for (String s : viewer){
            System.out.println(s);
        }
    }
}
