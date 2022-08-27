package com.yl.task2;

import java.io.*;

import static com.yl.task2.PyramidSort.heapSort;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out)))
        {
            StrayArr[] temp_array = inputProcessing();
            for (int i = 0; i <= temp_array.length - 1; i++) {
                writer.write(temp_array[i].getCount() + " ");
            }
            writer.flush();
        }
    }

    public static StrayArr[] inputProcessing() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            // указывается размер arraySize.
            int arraySize = Integer.parseInt(reader.readLine());

            StrayArr[] struct = new StrayArr[arraySize];

            // любая строка для рассчета хеша.
            String line = reader.readLine();

            for (int i = 0; i < arraySize; i++) {
                line = getHash(line.toCharArray());
                long count = Long.parseLong(line);
                struct[i] = new StrayArr(count);
            }

            return heapSort(struct);

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