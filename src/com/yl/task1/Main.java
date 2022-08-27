package com.yl.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.yl.task1.PyramidSort.heapSort;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(inputProcessing());
    }

    public static String inputProcessing() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            // указывается размер arraySize.
            int arraySize = Integer.parseInt(reader.readLine());

            StrayArr[] struct = new StrayArr[arraySize];

            // любая строка для рассчета хеша.
            String line = reader.readLine();

            long mean = 0;
            for (int i = 0; i < arraySize; i++) {
                line = getHash(line.toCharArray());
                long count = Long.parseLong(line);
                struct[i] = new StrayArr(count);
                mean += count;
            }

            heapSort(struct);

            return "Элементы {" +
                    "Минимальное значение = '" + struct[struct.length - 1].getCount() + '\'' +
                    ", Максимальное значение = '" + struct[0].getCount() + '\'' +
                    ", Среднее значение = '" + mean / arraySize + '\'' +
                    '}';
        }
    }

    public static String getHash(char[] line) {
        long hash = 0;
        for (char c : line) {
            hash = (hash * 123 + (int) c) % 100003;
        }
        return String.valueOf(hash);
    }
}