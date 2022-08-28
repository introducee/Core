package com.yl.task2;

import java.io.*;

import static com.yl.task2.PyramidSort.heapSort;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out)))
        {
            Integer[] temp_array = inputProcessing();
            for (int i = 0; i <= temp_array.length - 1; i++) {
                writer.write(temp_array[i] + " ");
            }
            writer.flush();
        }
    }

    public static Integer[] inputProcessing() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            // указывается размер arraySize.
            int arraySize = Integer.parseInt(reader.readLine());

            Integer[] struct = new Integer[arraySize];

            // ввод элементов массива через пробел.
            String[] line = reader.readLine().split(" ");

            for (int i = 0; i < arraySize; i++) {
                int count = Integer.parseInt(line[i]);
                struct[i] = count;
            }

            return heapSort(struct);

        }
    }
}