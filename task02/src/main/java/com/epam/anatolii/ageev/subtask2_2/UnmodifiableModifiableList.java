package com.epam.anatolii.ageev.subtask2_2;

import com.epam.anatolii.ageev.subtask2_2.exceptions.UnmodifiablePartAccessExseption;

import java.util.*;

public class UnmodifiableModifiableList<T> implements List<T> {
    private final List<T> unmodifiablePart;
    private List<T> modifiablePart;

    public UnmodifiableModifiableList(List<T> unmodifiable, List<T> modifiable) {
        unmodifiablePart = unmodifiable;
        modifiablePart = modifiable;
    }

    @Override
    public int size() {
        return unmodifiablePart.size() + modifiablePart.size();
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return unmodifiablePart.contains(o) || modifiablePart.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<T> {
        private int cursor;

        @Override
        public boolean hasNext() {
            return cursor < size();
        }

        @Override
        public T next() {
            int index = cursor;
            if (index < 0 || index > size() - 1) {
                throw new NoSuchElementException();
            }
            cursor = index + 1;
            return get(index);
        }
    }

    @Override
    public Object[] toArray() {
        Object[] copyObjectList = new Object[this.size()];
        System.arraycopy(unmodifiablePart.toArray(), 0, copyObjectList, 0, unmodifiablePart.size());
        System.arraycopy(modifiablePart.toArray(), 0, copyObjectList, unmodifiablePart.size(), modifiablePart.size());
        return copyObjectList;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < this.size()) {
            return (T1[]) Arrays.copyOf(toArray(), this.size(), a.getClass());
        }
        System.arraycopy(toArray(), 0, a, 0, this.size());
        return a;
    }

    @Override
    public boolean add(T t) {
        return modifiablePart.add(t);
    }

    @Override
    public boolean remove(Object o) {
        if (unmodifiablePart.contains(o)) {
            throw new UnmodifiablePartAccessExseption("The element is contained in an immutable part of the list");
        }
        return modifiablePart.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return c.stream().allMatch(this::contains);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return modifiablePart.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        checkNullItem(c);
        checkIndex(index);
        if (index >= unmodifiablePart.size()) {
            return modifiablePart.addAll(index - unmodifiablePart.size(), c);
        } else {
            throw new UnmodifiablePartAccessExseption("The element is contained in an immutable part of the list");
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        checkNullItem(c);
        if (c.stream().anyMatch(unmodifiablePart::contains)) {
            throw new UnmodifiablePartAccessExseption("The element is contained in an immutable part of the list");
        } else {
            return modifiablePart.removeAll(c);
        }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        checkNullItem(c);
        if (c.stream().allMatch(unmodifiablePart::contains)) {
            return modifiablePart.retainAll(c);
        } else {
            throw new UnmodifiablePartAccessExseption("The element is contained in an immutable part of the list");
        }
    }

    @Override
    public void clear() {
        if (unmodifiablePart.isEmpty()) {
            throw new UnmodifiablePartAccessExseption("Collection contains unmodifiable part. This operation illegal.");
        }
        modifiablePart.clear();
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        if (index >= unmodifiablePart.size()) {
            return modifiablePart.get(index - unmodifiablePart.size());
        }
        return unmodifiablePart.get(index);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= unmodifiablePart.size() + modifiablePart.size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);
        if (index >= unmodifiablePart.size()) {
            return modifiablePart.set(index - unmodifiablePart.size(), element);
        } else {
            throw new UnmodifiablePartAccessExseption("The element is contained in an immutable part of the list");
        }
    }

    @Override
    public void add(int index, T element) {
        checkIndex(index);
        if (index >= unmodifiablePart.size()) {
            modifiablePart.add(index - unmodifiablePart.size(), element);
        } else {
            throw new UnmodifiablePartAccessExseption("The element is contained in an immutable part of the list");
        }
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        if (index >= unmodifiablePart.size()) {
            return modifiablePart.remove(index - unmodifiablePart.size());
        } else {
            throw new UnmodifiablePartAccessExseption("The element is contained in an immutable part of the list");
        }
    }

    @Override
    public int indexOf(Object o) {
        checkNullItem(o);
        if (unmodifiablePart.contains(o)) {
            return unmodifiablePart.indexOf(o);
        }
        if (modifiablePart.contains(o)) {
            return unmodifiablePart.size() + modifiablePart.indexOf(o);
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        checkNullItem(o);
        if (modifiablePart.contains(o)) {
            return unmodifiablePart.size() + modifiablePart.lastIndexOf(o);
        }
        if (unmodifiablePart.contains(o)) {
            return unmodifiablePart.lastIndexOf(0);
        }
        return -1;
    }

    private void checkNullItem(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
