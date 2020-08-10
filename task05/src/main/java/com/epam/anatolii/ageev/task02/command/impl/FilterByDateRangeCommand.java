package com.epam.anatolii.ageev.task02.command.impl;

import com.epam.anatolii.ageev.task02.command.Command;
import com.epam.anatolii.ageev.task02.search_filter.FileDateRangeFilter;
import com.epam.anatolii.ageev.task02.search_filter.SearchFilterImpl;
import com.epam.anatolii.ageev.task02.utils.CommandUtils;

public class FilterByDateRangeCommand implements Command {
    @Override
    public SearchFilterImpl execute(SearchFilterImpl searchFilter) {
        String outputChoice = "Search by file dates Y/N: 1/0";
        String message1 = "Please enter date From";
        String message2 = "Please enter date To";
        if (CommandUtils.userInput(outputChoice) == 1) {
            Long dateFrom = CommandUtils.enterDate(message1).getTime();
            Long dateTo = CommandUtils.enterDate(message2).getTime();
            searchFilter = new FileDateRangeFilter(searchFilter, dateFrom, dateTo);
        }
        return searchFilter;
    }
}
