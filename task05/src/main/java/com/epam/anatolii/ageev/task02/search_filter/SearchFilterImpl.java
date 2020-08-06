package com.epam.anatolii.ageev.task02.search_filter;

import java.io.File;

public abstract class SearchFilterImpl implements SearchFilter {
    private SearchFilter nextSearchFilter;

    public void setNextSearchFilter(SearchFilter nextSearchFilter) {
        this.nextSearchFilter = nextSearchFilter;
    }

    protected boolean hasNext(){
       return nextSearchFilter != null;
    }

    protected boolean searchNext(File file) {
        if (!hasNext()) {
            return true;
        }
        return nextSearchFilter.search(file);
    }
}
