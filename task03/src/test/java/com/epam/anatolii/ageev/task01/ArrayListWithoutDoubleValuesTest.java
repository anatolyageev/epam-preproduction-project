package com.epam.anatolii.ageev.task01;

import com.epam.anatolii.ageev.task01.exceptions.DoubleValueExceptions;
import org.junit.Before;
import org.junit.Test;
import com.epam.anatolii.ageev.entity.Item;
import com.epam.anatolii.ageev.entity.Server;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ArrayListWithoutDoubleValuesTest {

    private List<Item> itemList;

    @Before
    public void before() {
        itemList = new ArrayListWithoutDoubleValues<>();
        itemList.add(new Server(1L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true));
        itemList.add(new Server(2L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true));
        itemList.add(new Server(3L, 1003.00, "Intel core 9", 4.5, 48, "FX", 2, true));
        itemList.add(new Server(4L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true));
        itemList.add(new Server(5L, 1003.00, "Intel core 9", 4.5, 48, "FX", 2, true));
        itemList.add(new Server(6L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true));
        itemList.add(new Server(7L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true));
    }


    @Test(expected = DoubleValueExceptions.class)
    public void setDuplicateElementToTheList_ShouldThrowException() {
        itemList.set(itemList.indexOf(new Server(1L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true))
                , new Server(1L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true));
    }

    @Test(expected = DoubleValueExceptions.class)
    public void addDuplicateElementToTheList_ShouldThrowException() {
        itemList.add(new Server(1L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true));
    }

    @Test(expected = DoubleValueExceptions.class)
    public void addIndexDuplicateElementToTheList_ShouldThrowException() {
        itemList.add(3,new Server(1L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true));
    }

    @Test
    public void addElementToTheList_ShouldReturnTrue() {
       assertTrue(itemList.add(new Server(10L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true)));
    }

    @Test
    public void addAllTestAddingList_shouldReturnTrue() {
        List<Item> testItemList = new ArrayList<>();
        testItemList.add(new Server(8L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true));
        testItemList.add(new Server(9L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true));

        assertTrue(itemList.addAll(testItemList));
        for (Item i : itemList) {
            System.out.println(i);
        }
    }

    @Test(expected = DoubleValueExceptions.class)
    public void addAllTestAddingListWithDuplicates_ShouldThrowException() {
        List<Item> testItemList = new ArrayList<>();
        testItemList.add(new Server(8L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true));
        testItemList.add(new Server(9L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true));
        testItemList.add(new Server(9L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true));

        itemList.addAll(testItemList);
    }
}