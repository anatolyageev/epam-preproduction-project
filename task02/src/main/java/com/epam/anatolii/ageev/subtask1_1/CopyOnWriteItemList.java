package com.epam.anatolii.ageev.subtask1_1;

import java.util.*;
import java.util.function.Predicate;
import com.epam.anatolii.ageev.entity.Item;

public class CopyOnWriteItemList<T extends Item> implements List<Item> {

    /**
     * Default initial capacity.
     */
    private static final int INITIAL_CAPACITY = 10;

    private int size;
    private Item[] itemArray;

    public CopyOnWriteItemList() {
        this.itemArray = new Item[INITIAL_CAPACITY];
    }

    public CopyOnWriteItemList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
        this.itemArray = new Item[capacity];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<Item> iterator() {
        return new IteratorImpl();
    }

    public Iterator<T> iteratorCustom(Predicate<T> predicate) {
        return new IteratorCustom(predicate);
    }

    private class IteratorCustom implements Iterator<T> {
        private int cursor;
        private Predicate<T> predicate;
        private int sizeIterator;
        private Item[] itemArrayIterator;

        public IteratorCustom(Predicate<T> predicate) {
            itemArrayIterator = itemArray;
            sizeIterator = size;
            this.predicate = predicate;
        }

        @Override
        public boolean hasNext() {
            for (int next = cursor + 1; next < sizeIterator; next++) {
                if (predicate.test((T) itemArrayIterator[next])) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public T next() {
            do {
                if (cursor > sizeIterator - 1) {
                    throw new NoSuchElementException();
                }
                cursor++;
            } while (!predicate.test((T) itemArrayIterator[cursor]));
            return (T) itemArrayIterator[cursor];
        }
    }

    private class IteratorImpl implements Iterator<Item> {
        private int cursor;
        private int sizeIterator;
        private Item[] itemArrayIterator;


        public IteratorImpl(){
            itemArrayIterator = itemArray;
            sizeIterator = size;
        }

        @Override
        public boolean hasNext() {
            return cursor < sizeIterator;
        }

        @Override
        public Item next() {
            int index = cursor;
            if (index < 0 || index > sizeIterator - 1) {
                throw new NoSuchElementException();
            }
            cursor = index + 1;
            return itemArrayIterator[index];
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.itemArray, this.size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) toArray();
        }
        System.arraycopy(itemArray, 0, a, 0, size);
        return a;
    }

    @Override
    public boolean add(Item item) {
        checkCapacity();
        itemArray[size++] = item;
        return true;
    }

    private void checkCapacity() {
        if (size + 1 == itemArray.length) {
            arrayResize();
        }else{
            itemArray = Arrays.copyOf(itemArray, itemArray.length);
        }
    }

    private void arrayResize() {
        int newSize = itemArray.length << 1;
        itemArray = Arrays.copyOf(itemArray, newSize);
    }

    private void arrayResize(int newSize) {
        itemArray = Arrays.copyOf(itemArray, newSize);
    }

    @Override
    public boolean remove(Object o) {
        checkCapacity();
        int index = indexOf(o);
        if (index == -1) {
            return false;
        }

        System.arraycopy(itemArray, index + 1, itemArray, index, --size - index);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return c.stream().allMatch(i -> contains(i));
    }

    @Override
    public boolean addAll(Collection<? extends Item> c) {
        checkNullItem(c);
        if (this.size + c.size() >= itemArray.length) {
            arrayResize(this.size + c.size());
        }
        System.arraycopy(c.toArray(), 0, itemArray, size, c.size());
        size += c.size();
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Item> c) {
        checkNullItem(c);
        checkIndexForAddition(index);
        checkCapacity();
        if (this.size + c.size() >= itemArray.length) {
            arrayResize(this.size + c.size());
        }

        System.arraycopy(itemArray, index, itemArray, index + c.size(), size - index);
        System.arraycopy(c.toArray(), 0, itemArray, index, c.size());
        size += c.size();
        return true;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkIndexForAddition(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        checkCapacity();
        boolean result = false;
        for (Object item : c) {
            if (contains(item)) {
                remove(item);
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        checkCapacity();
        boolean result = false;
        Item[] retainArr = new Item[size];
        int index = 0;

        for (Object item : c) {
            if (contains(item)) {
                retainArr[index++] = (Item) item;
            }
        }

        itemArray = retainArr;
        result = index != size;

        size = index;
        return result;
    }

    @Override
    public void clear() {
        itemArray = new Item[INITIAL_CAPACITY];
        this.size = 0;
    }

    @Override
    public Item get(int index) {
        checkIndex(index);
        return itemArray[index];
    }

    @Override
    public Item set(int index, Item element) {
        checkIndex(index);
        checkCapacity(); // method will make copy of array if no resize;
        Item item = itemArray[index];
        itemArray[index] = element;
        return item;
    }

    @Override
    public void add(int index, Item element) {
        checkIndex(index);
        checkCapacity();
        System.arraycopy(itemArray, index, itemArray, index + 1, size++ - index);
        itemArray[index] = element;
    }

    @Override
    public Item remove(int index) {
        checkIndex(index);
        checkCapacity();
        Item item = itemArray[index];
        System.arraycopy(itemArray, index + 1, itemArray, index, --size - index);
        return item;
    }

    @Override
    public int indexOf(Object o) {
        checkNullItem(o);
        T item = (T) o;
        for (int i = 0; i < this.size; i++) {
            if (item.equals(itemArray[i])) {
                return i;
            }
        }
        return -1;
    }

    private void checkNullItem(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
    }

    @Override
    public int lastIndexOf(Object o) {
        checkNullItem(o);
        T item = (T) o;
        for (int i = this.size - 1; i >= 0; i--) {
            if (item.equals(itemArray[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<Item> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<Item> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Item> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}