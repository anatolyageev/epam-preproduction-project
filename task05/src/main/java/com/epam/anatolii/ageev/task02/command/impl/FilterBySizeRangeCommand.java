package com.epam.anatolii.ageev.task02.command.impl;

import com.epam.anatolii.ageev.task02.command.Command;
import com.epam.anatolii.ageev.task02.search_filter.FileSizeRangeFilter;
import com.epam.anatolii.ageev.task02.search_filter.SearchFilterImpl;
import com.epam.anatolii.ageev.task02.utils.CommandUtils;

public class FilterBySizeRangeCommand implements Command {

    @Override
    public SearchFilterImpl execute(SearchFilterImpl searchFilter) {
        String outputChoice = "Search by file size Y/N: 1/0";
        String message1 = "Please enter size from: ";
        String message2 = "Please enter size to: ";
        if (CommandUtils.userInput(outputChoice) == 1) {
            Long sizeFrom = CommandUtils.longFilter(message1);
            Long sizeTo = CommandUtils.longFilter(message2);
            searchFilter = new FileSizeRangeFilter(searchFilter, sizeFrom, sizeTo);
        }
        return searchFilter;
    }
}
