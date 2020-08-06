package com.epam.anatolii.ageev.task02.search_filter;

import java.io.File;

public class FileExtensionFilter extends SearchFilterImpl {
    private String extension;

    public FileExtensionFilter(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean search(File file) {
        if(file.getName().endsWith(extension)){
            return searchNext(file);
        }
        return false;
    }
}
