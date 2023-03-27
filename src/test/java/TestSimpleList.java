import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import service.SimpleList;
import service.impl.SimpleListImpl;
import org.junit.jupiter.api.Test;
import utils.GenericComparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSimpleList {

    SimpleList<String> listStrings = new SimpleListImpl<>();

    @BeforeEach
    public void init() {
        listStrings.add("A");
        listStrings.add("C");
        listStrings.add("Ac");
        listStrings.add("C");
        listStrings.add("Ab");
        listStrings.add("C");
    }

    @AfterEach
    public void clearAll() throws Exception {
        for (int i = 0; i < listStrings.size(); i++) {
            listStrings.remove(i);
        }
    }

    @Test
    public void testAdd() {
        listStrings.add("D");
        assertEquals(7, listStrings.size());
    }

    @Test
    public void testInsert() throws Exception {
        listStrings.insert(3, "O");
        assertEquals("O", listStrings.get(3));
    }

    @Test
    public void testRemove() throws Exception {
        listStrings.remove(0);
        assertEquals(5, listStrings.size());
    }

    @Test
    public void testGet() {
        assertEquals("A", listStrings.get(0));
    }

    @Test
    public void testSize() {
        assertEquals(6, listStrings.size());
    }

    @Test
    public void testAddAll() {
        SimpleList<String> secondListStrings = new SimpleListImpl<>();

        secondListStrings.add("K");
        secondListStrings.add("L");
        secondListStrings.add("K");
        secondListStrings.add("L");
        secondListStrings.add("K");
        secondListStrings.add("L");
        secondListStrings.add("K");
        secondListStrings.add("L");
        secondListStrings.add("K");
        secondListStrings.add("L");

        listStrings.addAll(secondListStrings);

        assertEquals(16, listStrings.size());
    }

    @Test
    public void testFirst() {
        assertEquals(1, listStrings.first("C"));
    }

    @Test
    public void testLast() {
        assertEquals(5, listStrings.last("C"));
    }

    @Test
    public void testIsEmpty() throws Exception {

        while (listStrings.get(0) != null) {
            listStrings.remove(0);
        }

        assertEquals(0, listStrings.size());
    }

    @Test
    public void testSort() {
        SimpleList<String> simpleList = listStrings.sort(new GenericComparator<>());
        assertEquals("Ab", simpleList.get(1));
    }
}
