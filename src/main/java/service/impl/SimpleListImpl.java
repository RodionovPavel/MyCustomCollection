package service.impl;

import exception.MyEmptyListException;
import exception.MyWrongIndexException;
import service.SimpleList;
import java.util.*;
import java.util.stream.IntStream;

public class SimpleListImpl<T> implements SimpleList<T> {
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] list;

    public SimpleListImpl() {
        list = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T item) {
        if (size == list.length)
            growArray();
        list[size] = item;
        size++;
    }

    private void growArray() {
        Object[] newArray = new Object[list.length * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = list[i];
        }
        list = newArray;
    }

    public void growArray(int capacity) {
        Object[] newArray = new Object[list.length + capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = list[i];
        }
        list = newArray;
    }

    @Override
    public void insert(int index, T item) throws MyWrongIndexException {
        if (index < 0 || index > size - 1) {
            throw new MyWrongIndexException("Индекс в коллекции отсутствует.");
        }
        list[index] = item;
    }

    @Override
    public void remove(int index) throws MyWrongIndexException {
        if (index > size - 1) {
            throw new MyWrongIndexException("Индекс в коллекции отсутствует.");
        }

        Object[] tempArray = new Object[list.length];

        for (int i = 0; i < list.length - 1; i++) {
            if (i < index) {
                tempArray[i] = list[i];
            } else {
                tempArray[i] = list[i + 1];
            }
        }

        list = tempArray;
        size--;
    }

    @Override
//    public Optional<T> get(int index) {
//        return Optional.ofNullable((T) list[index]);
//    }
    public Object get(int index) {
        return list[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addAll(SimpleList<T> newList) {
        if ((size + newList.size()) > list.length) {
            growArray(newList.size());
        }
        for (int i = 0; i < newList.size(); i++) {
            list[i + size] = newList.get(i);
        }
        size += newList.size();
    }

    @Override
    public int first(T item) {
        return IntStream.range(0, list.length)
                .filter(i -> list[i] == item)
                .findFirst().orElse(-1);
    }

    @Override
    public int last(T item) {
        return IntStream.range(0, list.length)
                .map(i -> list.length - i - 1)
                .filter(i -> list[i] == item)
                .findFirst().orElse(-1);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public SimpleList<T> shuffle() throws MyEmptyListException {

        if (size == 0) {
            throw new MyEmptyListException("Список пуст!");
        }

        SimpleList<T> simpleList = new SimpleListImpl<>();
        var tempArray = copyArray();

        Random random = new Random();
        for (int i = size - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            var temp = tempArray[index];
            tempArray[index] = tempArray[i];
            tempArray[i] = temp;
        }

        return fromArrayToSimpleList(tempArray, simpleList);
    }

    private Object[] copyArray() {

        Object[] newArray = new Object[list.length];
        for (int i = 0; i < size; i++) {
            newArray[i] = list[i];
        }

        return newArray;
    }

    private SimpleList<T> fromArrayToSimpleList(Object[] tempArray, SimpleList<T> simpleList) {

        for (Object t : tempArray) {
            simpleList.add((T) t);
        }

        return simpleList;
    }

    @Override
    public SimpleList<T> sort(Comparator<T> comparator) {
        SimpleList<T> simpleList = new SimpleListImpl<>();
        var tempArray = copyArray();
        boolean needIteration = true;

        while (needIteration) {
            needIteration = false;
            for (int i = 0; i < size - 1; i++) {
                if ((comparator.compare((T) tempArray[i], (T) tempArray[i + 1])) > 0) {
                    var temp = tempArray[i];
                    tempArray[i] = tempArray[i + 1];
                    tempArray[i + 1] = temp;
                    needIteration = true;
                }
            }
        }

        return fromArrayToSimpleList(tempArray, simpleList);
    }
}


