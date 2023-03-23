import service.SimpleList;
import service.impl.SimpleListImpl;
import utils.GenericComparator;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws Exception {
        SimpleList<String> listStringFirst = new SimpleListImpl<>();
        SimpleList<String> listStringSecond = new SimpleListImpl<>();
        SimpleList<Integer> listInt = new SimpleListImpl<>();

        listInt.add(4);
        listInt.add(5);
        listInt.add(1);

        listStringFirst.add("A");
        listStringFirst.add("C");
        listStringFirst.add("Ac");
        listStringFirst.add("C");
        listStringFirst.add("Ab");
        listStringFirst.add("C");




        System.out.println("Original list:");
        System.out.println(listInt);
        System.out.println(listStringFirst);
        System.out.println("List after shuffle:");
        System.out.println(listInt.shuffle());
        System.out.println(listStringFirst.shuffle());

        System.out.println("List after sort:");
        System.out.println(listInt.sort(new GenericComparator<>()));
        System.out.println(listStringFirst.sort(new GenericComparator<>()));

        System.out.println(listStringFirst.first("PPP"));
        System.out.println(listStringFirst.last("C"));

        listStringSecond.add("1");
        listStringSecond.add("2");
        listStringSecond.add("3");
        listStringSecond.add("3");
        listStringSecond.add("3");
        listStringSecond.add("3");
        listStringSecond.add("3");
        listStringSecond.add("3");

        listStringFirst.addAll(listStringSecond);
        System.out.println(listStringFirst);
    }
}
