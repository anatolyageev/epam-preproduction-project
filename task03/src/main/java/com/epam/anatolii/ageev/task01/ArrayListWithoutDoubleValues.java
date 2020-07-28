package com.epam.anatolii.ageev.task01;

import com.epam.anatolii.ageev.task01.exceptions.DoubleValueExceptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ArrayListWithoutDoubleValues<T> extends ArrayList<T> {

    @Override
    public boolean add(T element) {
        checkForDoubleValue(element);
        return super.add(element);
    }

    @Override
    public void add(int index, T element) {
        checkForDoubleValue(element);
        super.add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (isDoubleValueExistInCollection(collection)) {
            throw new DoubleValueExceptions("Collection contains duplicates");
        }
        checkForDoubleValueInCollection(collection);
        return super.addAll(collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        if (isDoubleValueExistInCollection(collection)) {
            throw new DoubleValueExceptions("Collection contains duplicates");
        }
        checkForDoubleValueInCollection(collection);
        return super.addAll(index, collection);
    }

    @Override
    public T set(int index, T element) {
        checkForDoubleValue(element);
        return super.set(index, element);
    }

    private void checkForDoubleValue(T element) {
        if (contains(element)) {
            throw new DoubleValueExceptions("This element already exist in the list");
        }
    }

    private void checkForDoubleValueInCollection(Collection<? extends T> collection) {
        for (T item : collection) {
            checkForDoubleValue(item);
        }
    }

    private boolean isDoubleValueExistInCollection(Collection<? extends T> collection) {
        Set set = new HashSet(collection);
        if (set.size() == collection.size()) {
            return false;
        }
        return true;
    }
}
