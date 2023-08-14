package me.don1ns.myarraylist;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {
    @Test
    public void testAddAndGet() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        assertEquals(10, (int) list.get(0));
        assertEquals(20, (int) list.get(1));
        assertEquals(30, (int) list.get(2));
        assertEquals(3, list.getSize());
    }

    @Test
    public void testAddAtIndex() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");
        list.add(1, "orange");
        assertEquals("apple", list.get(0));
        assertEquals("orange", list.get(1));
        assertEquals("banana", list.get(2));
        assertEquals("cherry", list.get(3));
        assertEquals(4, list.getSize());
    }

    @Test
    public void testAddAtIndexWithInvalidIndex() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("apple");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add(2, "banana");
        });
        assertEquals(1, list.getSize());
    }

    @Test
    public void testRemove() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");
        String removed = list.remove(1);
        assertEquals("banana", removed);
        assertEquals(2, list.getSize());
        assertEquals("apple", list.get(0));
        assertEquals("cherry", list.get(1));
    }

    @Test
    public void testRemoveWithInvalidIndex() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("apple");
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(1);
        });
        assertEquals(1, list.getSize());
    }

    @Test
    public void testClear() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");
        list.clear();
        assertEquals(0, list.getSize());
    }

    @Test
    public void testQuickSort() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(5);
        list.add(2);
        list.add(8);
        list.add(1);
        list.add(9);
        list.quickSort(Comparator.naturalOrder());
        assertEquals(1, (int) list.get(0));
        assertEquals(2, (int) list.get(1));
        assertEquals(5, (int) list.get(2));
        assertEquals(8, (int) list.get(3));
        assertEquals(9, (int) list.get(4));
    }
    @Test
    void trimToSize() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        MyArrayList<Integer> list1 = new MyArrayList<>(5);
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.remove(3);

        assertNotEquals(list, list1);

        list1.trimToSize();

        assertEquals(list, list1);
    }

}