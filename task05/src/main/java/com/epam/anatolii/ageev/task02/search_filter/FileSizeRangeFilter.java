package com.epam.anatolii.ageev.task02.search_filter;

import java.io.File;

public class FileSizeRangeFilter extends SearchFilterImpl {
    private Long sizeFrom;
    private Long sizeTo;

    public FileSizeRangeFilter(SearchFilter nextSearchFilter, Long sizeFrom, Long sizeTo) {
        super(nextSearchFilter);
        this.sizeFrom = sizeFrom;
        this.sizeTo = sizeTo;
    }

    @Override
    public boolean search(File file) {
        if (file.length() >= sizeFrom && file.length() <= sizeTo) {
            return searchNext(file);
        }
        return false;
    }
}
