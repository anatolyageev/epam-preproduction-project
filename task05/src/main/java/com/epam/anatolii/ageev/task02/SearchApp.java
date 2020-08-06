package com.epam.anatolii.ageev.task02;

import java.io.File;
import java.util.List;

public class SearchApp {
    public static void main(String[] args) {
        SearchSettings searchSettings = new SearchSettings();
        List<File> searchResult = searchSettings.start();
        if (searchResult.size() > 0) {
            System.out.println("Result:");
            searchResult.forEach(file -> System.out.println(file.getAbsolutePath()));
        } else {
            System.out.println("Nothing can be find");
        }
    }
}
