package com.epam.anatolii.ageev.task02;

import java.util.Objects;

public class HashFunctionLength {
    private String string;

    public HashFunctionLength(String string) {
        this.string = string;
    }

    @Override
    public int hashCode() {
        return string.length();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HashFunctionLength)) return false;
        HashFunctionLength that = (HashFunctionLength) o;
        return Objects.equals(string, that.string);
    }
}
