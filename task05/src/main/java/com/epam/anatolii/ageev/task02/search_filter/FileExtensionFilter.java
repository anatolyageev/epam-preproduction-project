package com.epam.anatolii.ageev.task02.search_filter;

import java.io.File;

public class FileExtensionFilter extends BaseSearchFilter {
    private String extension;

    public FileExtensionFilter(SearchFilter nextSearchFilter, String extension) {
        super(nextSearchFilter);
        this.extension = extension;
    }

    @Override
    public boolean search(File file) {
        return searchNext(file);
    }
}
