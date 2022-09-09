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
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            Integer[] arr = new Integer[tokenizer.countTokens()];
            for (int index = 0; tokenizer.hasMoreTokens(); index++) {
                String st = tokenizer.nextToken();
                if(st.matches("^[0-9]+$")) {
                    arr[index] = Integer.parseInt(st);
                } else {
                    return "Некорректный ввод";
                }
            }

            // ввод заданного числа.
            String givenNumber = reader.readLine();
            int search;

            if(givenNumber.matches("^[0-9]+$")) {
                search = Integer.parseInt(givenNumber);
            } else {
                return "Некорректный ввод";
            }


            Map<Integer, int[]> map = new HashMap<>();

            for (int element : arr) {
                if (!map.containsKey(element)) {
                    map.put(element, new int[2]);
                }
                map.get(element)[0] = search - element;
                ++map.get(element)[1];
            }

            for (int j : map.keySet()) {
                int value = map.get(j)[0];
                int repeat = map.get(j)[1];
                if (map.containsKey(value)) {
                    return j + " " + value;
                } else if (repeat > 1 && search % value == 0 && value * 2 == search) {
                    return value + " " + value;
                }
            }

            return "Не найдено";
        }
    }
}
