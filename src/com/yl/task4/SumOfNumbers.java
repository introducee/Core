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
            String[] lines = reader.readLine().split(" ");
            // ввод заданного числа.
            int search = Integer.parseInt(reader.readLine());

            if (check(lines)) {
                return "Некорректный ввод данных";
            }

            Map<Integer, int[]> map = new HashMap<>();

            for (String s : lines) {
                int num = Integer.parseInt(s);
                if (!map.containsKey(num)) {
                    map.put(num, new int[2]);
                }
                map.get(num)[0] = search - num;
                ++map.get(num)[1];
            }

            for (int key : map.keySet()) {
                int value = map.get(key)[0];
                int repeat = map.get(key)[1];
                if (map.containsKey(value) && (value != key || map.get(value)[1] > 1)) {
                    return key + " " + value;
                } else if (repeat > 1 && search % value == 0 && value * 2 == search) {
                    return value + " " + value;
                }
            }

            return "Не найдено";
        }
    }

    public static boolean check(String[] lines) {
        if (lines == null) {
            return true;
        }

        for (String line : lines) {
            if (line == null || line.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
