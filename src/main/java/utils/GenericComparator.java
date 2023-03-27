package utils;

import java.util.Comparator;

public class GenericComparator <T extends Comparable<T>> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        if (o1 == null) {
            return (o2 == null) ? 0 : -1;
        } else if (o2 == null) {
            return +1;
        } else {
            return o1.compareTo(o2);
        }
    }
}
