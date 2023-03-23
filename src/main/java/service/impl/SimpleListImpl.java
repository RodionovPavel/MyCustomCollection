package service.impl;

import service.SimpleList;

import java.util.*;
import java.util.stream.IntStream;

public class SimpleListImpl<T> implements SimpleList<T> {
    private int count;
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] list;

    private SimpleList<T> copyArray;

    public SimpleListImpl() {
        list = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T item) {
        if (count == list.length)
            growArray(0);
        list[count] = item;
        count++;
    }

    private void growArray(int capacity) {
        Object[] newArray = new Object[list.length * 2 + capacity];
        System.arraycopy(list, 0, newArray, 0, count - 1);
        list = newArray;
    }

    @Override
    public void insert(int index, T item) throws Exception {
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException();
        }
        list[index] = item;
    }

    @Override
    public void remove(int index) throws Exception {
        if (isEmpty() || index > count - 1) {
            throw new IllegalArgumentException();
        }
        System.arraycopy(list, index + 1, list, index, count - index - 1);
        list[index + 1] = null;
        count--;
    }

    @Override
    public Object get(int index) {
        return list[index];
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void addAll(SimpleList<T> newList) {
        if (newList.size() > count) {
            growArray(newList.size());
        }
        for (int i = 0; i < newList.size(); i++) {
            list[i + count - 1] = newList.get(i);
        }
    }

    @Override
    public int first(T item) {
        return IntStream.range(0, list.length).filter(i -> list[i] == item).findFirst().orElse(-1);
    }

    @Override
    public int last(T item) {
        return IntStream.range(0, list.length).map(i -> list.length - i - 1).filter(i -> list[i] == item).findFirst().orElse(-1);
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public SimpleList<T> shuffle() throws Exception {
        int index;
        T temp;
        copyArray = null;
        copyArray();

        Random random = new Random();
        for (int i = count - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = (T) copyArray.get(i);
            copyArray.insert(i, (T) copyArray.get(index));
            copyArray.insert(index, temp);
        }
        return copyArray;
    }

    @Override
    public SimpleList<T> sort(Comparator<T> comparator) {
        Object[] list2 = Arrays.copyOf(list, count);
        Arrays.sort(list2);

        SimpleList<T> copyArray2 = new SimpleListImpl<>();
        for (Object t : list2) {
            copyArray2.add((T) t);
        }
        return copyArray2;
    }

    private void copyArray() {
        copyArray = new SimpleListImpl<>();
        for (Object t : list) {
            copyArray.add((T) t);
        }
    }

    @Override
    public String toString() {
        return "SimpleListImpl{" +
                "list=" + Arrays.toString(list) +
                '}';
    }
}


