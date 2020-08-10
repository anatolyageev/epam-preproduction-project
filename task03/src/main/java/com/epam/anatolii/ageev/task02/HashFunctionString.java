package com.epam.anatolii.ageev.task02;

import java.util.Objects;

public class HashFunctionString {
    private static int SYMBOL_COUNT = 4;
    private String string;

    public HashFunctionString(String string) {
        this.string = string;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        int length = string.length() < SYMBOL_COUNT ? string.length() : SYMBOL_COUNT;
        for (int i = 0; i < length; i++) {
            hashCode += string.charAt(i);
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HashFunctionString)) return false;
        HashFunctionString that = (HashFunctionString) o;
        return string.equals(that.string);
    }
}
