package org.example;

public class App {
    public static void main(String[] args) {
        CustomArrayImpl list = new CustomArrayImpl<>();

        for (int i = 1; i < 5; i++) {
            list.add(i);
        }

        Integer[] list2 = {-1, -2, -3};

        list.addAll(0, list2);
        list.remove(0);
        System.out.println(list.isEmpty());
        System.out.println(list.size());
        System.out.println(list.toString());
    }
}
