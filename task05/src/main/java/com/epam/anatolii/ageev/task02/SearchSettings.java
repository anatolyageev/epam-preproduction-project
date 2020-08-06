package com.epam.anatolii.ageev.task02;

import com.epam.anatolii.ageev.task02.command.Command;
import com.epam.anatolii.ageev.task02.command.CommandContainer;
import com.epam.anatolii.ageev.task02.search_filter.DummyFilter;
import com.epam.anatolii.ageev.task02.search_filter.SearchFilterImpl;
import com.epam.anatolii.ageev.task02.utils.CommandUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchSettings {

    private SearchFilterImpl searchFilter;
    private List<File> resultList;

    public List<File> start() {
        CommandContainer commandContainer;
        String initDirectory = "";
        searchFilter = new DummyFilter();
        commandContainer = new CommandContainer();
        resultList = new ArrayList<>();
        boolean correctFolderFlag = false;
        while (!correctFolderFlag) {
            initDirectory = CommandUtils.stringFilter("Please specify initial directory for search:");
            if (new File(initDirectory).isDirectory()) {
                correctFolderFlag = true;
            }
        }
        for (Command command : commandContainer) {
            searchFilter = command.execute(searchFilter);
        }
        System.out.println("Searching ...");
        return fileSearch(new File(initDirectory));
    }

    private List<File> fileSearch(File file) {
        File[] files = file.listFiles();
        if (files == null) {
            return resultList;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                fileSearch(f);
            } else if (searchFilter.search(f)) {
                resultList.add(f);
            }
        }
        return resultList;
    }
}
