package com.epam.anatolii.ageev.task02.command;

import com.epam.anatolii.ageev.task02.search_filter.SearchFilterImpl;

public interface Command {
    void execute(SearchFilterImpl searchFilter);
}
