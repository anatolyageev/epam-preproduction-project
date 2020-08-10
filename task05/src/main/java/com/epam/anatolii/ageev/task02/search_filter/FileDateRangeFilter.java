package com.epam.anatolii.ageev.task02.search_filter;

import java.io.File;

public class FileDateRangeFilter extends SearchFilterImpl {
    private Long dateFrom;
    private Long dateTo;

    public FileDateRangeFilter(SearchFilter nextSearchFilter, Long dateFrom, Long dateTo) {
        super(nextSearchFilter);
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    @Override
    public boolean search(File file) {
        if (file.lastModified() >= dateFrom && file.lastModified() <= dateTo) {
            return searchNext(file);
        }
        return false;
    }
}
