package com.yl.task4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SumOfNumbers {
    public static void main(String[] args) throws IOException {
        System.out.println(new SumOfNumbers().processing());
    }

    public String processing() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            // ввод элементов массива через пробел.
            String[] line = reader.readLine().split(" ");
            // ввод заданного числа.
            int search = Integer.parseInt(reader.readLine());


            Map<Integer, int[]> map = new HashMap<>();

            for (String s : line) {
                int num = Integer.parseInt(s);
                if (!map.containsKey(num)) {
                    map.put(num, new int[2]);
                }
                map.get(num)[0] = search - num;
                ++map.get(num)[1];
            }

            for (int j : map.keySet()) {
                int value = map.get(j)[0];
                int repeat = map.get(j)[1];
                if (map.containsKey(value) && (value != j || map.get(value)[1] > 1)) {
                    return j + " " + value;
                } else if (repeat > 1 && search % value == 0 && value * 2 == search) {
                    return value + " " + value;
                }
            }

            return "Не найдено";
        }
    }
}
