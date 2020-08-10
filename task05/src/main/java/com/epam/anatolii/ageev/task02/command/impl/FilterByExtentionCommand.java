package com.epam.anatolii.ageev.task02.command.impl;

import com.epam.anatolii.ageev.task02.command.Command;
import com.epam.anatolii.ageev.task02.search_filter.FileExtensionFilter;
import com.epam.anatolii.ageev.task02.search_filter.SearchFilterImpl;
import com.epam.anatolii.ageev.task02.utils.CommandUtils;

public class FilterByExtentionCommand implements Command {
    @Override
    public SearchFilterImpl execute(SearchFilterImpl searchFilter) {
        String outputChoice = "Search by file extension Y/N: 1/0";
        String message = "Please enter extension";
        if (CommandUtils.userInput(outputChoice) == 1) {
            searchFilter = new FileExtensionFilter(searchFilter, CommandUtils.stringFilter(message));
        }
        return searchFilter;
    }
}
