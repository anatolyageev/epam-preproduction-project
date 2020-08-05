package com.epam.anatolii.ageev.task02;

import com.epam.anatolii.ageev.task02.search_filter.BaseSearchFilter;
import com.epam.anatolii.ageev.task02.search_filter.DummySearchFilter;
import com.epam.anatolii.ageev.task02.search_filter.FileNameSearchFilter;
import com.epam.anatolii.ageev.task02.search_filter.SearchFilter;

import java.io.File;
import java.util.Scanner;

public class SearchSettings {

    public SearchFilter setFilterByName(BaseSearchFilter searchFilter, File file) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Search by file name Y/N: 0/1");
        String fileName = scanner.nextLine();
        searchFilter = new FileNameSearchFilter(new DummySearchFilter(), fileName);
        return searchFilter;
    }

}
