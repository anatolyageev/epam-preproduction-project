package com.epam.anatolii.ageev.task02.search_filter;

import java.io.File;

public class FileNameSearchFilter extends SearchFilterImpl {
    private String fileName;

    public FileNameSearchFilter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean search(File file) {
        if(file.getName().contains(fileName)){
            return searchNext(file);
        }
        return false;
    }
}
