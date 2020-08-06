package com.epam.anatolii.ageev.task02;

import com.epam.anatolii.ageev.task02.command.Command;
import com.epam.anatolii.ageev.task02.command.CommandContainer;
import com.epam.anatolii.ageev.task02.search_filter.DummyFilter;
import com.epam.anatolii.ageev.task02.search_filter.SearchFilterImpl;
import com.epam.anatolii.ageev.task02.utils.CommandUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SearchSettings {

    private String initDirectory;
    private SearchFilterImpl searchFilter;
    private CommandContainer commandContainer;
    private List<File> resultList;

    public List<File> start() {
        searchFilter = new DummyFilter();
        commandContainer = new CommandContainer();
        resultList = new ArrayList<>();
        while (true) {
            initDirectory = CommandUtils.stringFilter("Please specify initial directory for search:");
            if (new File(initDirectory).isDirectory()) {
                break;
            }
        }
        for (Command command : commandContainer) {
            command.execute(searchFilter);
        }

        return fileSearch(new File(initDirectory));
    }

    private List<File> fileSearch(File file) {

        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                fileSearch(f);
            } else if (searchFilter.search(f)) {
                resultList.add(f);
            }
        }
        return resultList;
    }
}
