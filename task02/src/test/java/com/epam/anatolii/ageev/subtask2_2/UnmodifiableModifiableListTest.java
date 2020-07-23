package com.epam.anatolii.ageev.subtask2_2;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class UnmodifiableModifiableListTest {

    List<Integer> testUnmodifiableModifiableList;
    List<Integer> modifiable;
    List<Integer> unmodifiable;


    @Before
    public void before() {
        unmodifiable = Arrays.asList(1, 2, 3, 4);
        modifiable = Arrays.asList(5, 6, 7, 8, 9, 10);
        testUnmodifiableModifiableList = new UnmodifiableModifiableList<>(unmodifiable, modifiable);
    }

    @Test
    public void testMethodToArray_ShouldReturnTrue() {
        Object[] testToArray = testUnmodifiableModifiableList.toArray();
        assertTrue(unmodifiable.size() + modifiable.size() == testUnmodifiableModifiableList.size());
    }

    @Test
    public void testMethodContainsAll_ShouldReturnTrue() {
        List<Integer> integerArrayList = new ArrayList<>(testUnmodifiableModifiableList);
        assertTrue(testUnmodifiableModifiableList.containsAll(integerArrayList));
    }

    @Test
    public void testGetMethod_ShouldReturnTrue() {
        assertTrue(testUnmodifiableModifiableList.get(unmodifiable.size() - 1).equals(unmodifiable.get(unmodifiable.size() - 1)));
        assertTrue(testUnmodifiableModifiableList.get(testUnmodifiableModifiableList.size() - 1).equals(modifiable.get(modifiable.size() - 1)));
    }

    @Test
    public void testIterator_shouldReturnFalse() {
        UnmodifiableModifiableList<Integer> iteratorTest = new UnmodifiableModifiableList<>(Arrays.asList(1), Arrays.asList(2));
        Iterator<Integer> iterator = iteratorTest.iterator();
        iterator.next();
        iterator.next();

        assertFalse(iterator.hasNext());
    }

}