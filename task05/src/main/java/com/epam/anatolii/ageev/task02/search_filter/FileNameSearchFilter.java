package com.epam.anatolii.ageev.task02.search_filter;

import java.io.File;

public class FileNameSearchFilter extends BaseSearchFilter {
    private String fileName;

    public FileNameSearchFilter(SearchFilter nextSearchFilter, String fileName) {
        super(nextSearchFilter);
        this.fileName = fileName;
    }

    @Override
    public boolean search(File file) {
        return searchNext(file);
    }
}
