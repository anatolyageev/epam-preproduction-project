package com.epam.anatolii.ageev.container;

import com.epam.anatolii.ageev.entity.Item;
import com.epam.anatolii.ageev.entity.Server;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class ItemListTest {

    private ItemList<Item> itemList;

    @Before
    public void before() {
        itemList = new ItemList<>();
        itemList.add(new Server(1L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true));
        itemList.add(new Server(2L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true));
        itemList.add(new Server(3L, 1003.00, "Intel core 9", 4.5, 48, "FX", 2, true));
        itemList.add(new Server(4L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true));
        itemList.add(new Server(5L, 1003.00, "Intel core 9", 4.5, 48, "FX", 2, true));
        itemList.add(new Server(6L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true));
        itemList.add(new Server(7L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true));
    }

    @Test
    public void addNewItem_returnTrue() {
        assertTrue(itemList.add(new Server(1L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true)));
    }

    @Test
    public void addNewItemByIndex_returnTrue() {
        Item item = new Server(8L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true);
        itemList.add(6, item);
        assertTrue(itemList.contains(item));
    }

    @Test
    public void getItem_returnTrue() {
        Item item = new Server(8L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true);
        itemList.add(6, item);
        assertTrue(itemList.get(6) == item);
    }

    @Test
    public void removeByIndex_shouldRemainSixItems() {
        int beforeSize = itemList.size();
        itemList.remove(0);
        assertTrue(itemList.size() == beforeSize - 1);
    }

    @Test
    public void removeByObject_shouldRemainFiveItems() {
        int beforeSize = itemList.size();
        itemList.remove(new Server(7L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true));
        assertTrue(itemList.size() == beforeSize - 1);
    }

    @Test
    public void customIteratorTest_resultListShouldContainTwoElement() {
        Iterator<Item> iterator = itemList.iteratorCustom(item -> item.getPrice() == 1003.00);
        List<Item> testItemList = new ItemList<>();

        while (iterator.hasNext()) {
            testItemList.add(iterator.next());
        }

        assertTrue(testItemList.size() == 2);
    }

    @Test
    public void addAllByIndex_LastElementShouldBeEqual() {
        int beforeSize = itemList.size();
        List<Item> arrayListItems = new ArrayList<>();
        arrayListItems.add(new Server(8L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true));
        arrayListItems.add(new Server(9L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true));

        itemList.addAll(6, arrayListItems);
        assertTrue(itemList.size() == arrayListItems.size() + beforeSize);
        assertEquals(itemList.get(itemList.size() - 1), new Server(7L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true));
    }

    @Test
    public void removeAll_ShouldReturnTrue() {
        int beforeSize = itemList.size();
        List<Item> arrayListItems = new ArrayList<>();
        arrayListItems.add(new Server(6L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true));
        arrayListItems.add(new Server(7L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true));

        itemList.removeAll(arrayListItems);

        assertTrue(itemList.size() == beforeSize - arrayListItems.size());
    }

    @Test
    public void retainAll_ShouldReturnTrue() {
        int beforeSize = itemList.size();
        List<Item> arrayListItems = new ArrayList<>();
        arrayListItems.add(new Server(6L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true));
        arrayListItems.add(new Server(7L, 1000.00, "Intel core 9", 4.5, 48, "FX", 2, true));

        itemList.retainAll(arrayListItems);

        assertTrue(itemList.size() == arrayListItems.size());
    }
}