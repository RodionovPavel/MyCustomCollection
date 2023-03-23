package service;

import java.util.Comparator;

public interface SimpleList<T> {
    void add(T item);

    void insert(int index, T item) throws Exception;

    void remove(int index) throws Exception;

    Object get(int index);

    int size();

    void addAll(SimpleList<T> list);

    int first(T item);
    int last(T item);
    boolean isEmpty();
    SimpleList<T> shuffle() throws Exception;
    SimpleList<T> sort(Comparator<T> comparator);
}
