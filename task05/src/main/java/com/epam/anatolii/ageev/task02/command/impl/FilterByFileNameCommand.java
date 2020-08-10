package com.epam.anatolii.ageev.task02.command.impl;

import com.epam.anatolii.ageev.task02.command.Command;
import com.epam.anatolii.ageev.task02.search_filter.FileNameSearchFilter;
import com.epam.anatolii.ageev.task02.search_filter.SearchFilterImpl;
import com.epam.anatolii.ageev.task02.utils.CommandUtils;

import java.util.Scanner;

public class FilterByFileNameCommand implements Command {
    @Override
    public SearchFilterImpl execute(SearchFilterImpl searchFilter) {
        String outputChoice = "Search by file name Y/N: 1/0";
        String message = "Please enter file name: ";
        if (CommandUtils.userInput(outputChoice) == 1) {
            searchFilter = new FileNameSearchFilter(searchFilter, CommandUtils.stringFilter(message));
        }
        return searchFilter;
    }
}
