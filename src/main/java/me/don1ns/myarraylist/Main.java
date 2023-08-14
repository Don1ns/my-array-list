package me.don1ns.myarraylist;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(3);
        list.add(1);
        list.add(7);
        list.add(2);
        list.add(8);
        System.out.println(list);
        list.add(2, 10);
        System.out.println(list);
        list.remove(2);
        System.out.println(list);
        list.trimToSize();
        System.out.println(list);
        list.quickSort(Comparator.naturalOrder());
        System.out.println(list);
        System.out.println(list.getSize());
    }
}