import service.SimpleList;
import service.impl.SimpleListImpl;
import utils.GenericComparator;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws Exception {
        SimpleList<String> list4 = new SimpleListImpl<>();
        SimpleList<Integer> list3 = new SimpleListImpl<>();

        list3.add(4);
        list3.add(5);
        list3.add(1);

        list4.add("A");
        list4.add("C");
        list4.add("Ac");
        list4.add("C");
        list4.add("Ab");
        list4.add("C");

        System.out.println("Original list:");
        System.out.println(list3);
        System.out.println(list4);
        System.out.println("List after shuffle:");
        System.out.println(list3.shuffle());
        System.out.println(list4.shuffle());

        System.out.println("List after sort:");
        System.out.println(list3.sort(new GenericComparator<>()));
        System.out.println(list4.sort(new GenericComparator<>()));

        System.out.println(list4.first("C"));
        System.out.println(list4.last("C"));
    }
}
