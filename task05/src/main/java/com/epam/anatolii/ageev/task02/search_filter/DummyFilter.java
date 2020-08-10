package com.epam.anatolii.ageev.task02.search_filter;

import java.io.File;

public class DummyFilter extends SearchFilterImpl {

    @Override
    public boolean search(File file) {
        if (!hasNext()) {
            return true;
        }
        return searchNext(file);
    }
}
