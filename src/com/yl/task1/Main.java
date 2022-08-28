package com.yl.task1;

import java.io.*;

import static com.yl.task1.PyramidSort.heapSort;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(inputProcessing());
    }

    public static String inputProcessing() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            // указать размер arraySize.
            int arraySize = Integer.parseInt(reader.readLine());
            // указать количество элементов в массиве.
            int amountElements = Integer.parseInt(reader.readLine());

            // ввод любой строки для рассчета хеша.
            String line = reader.readLine();

            int numberOfSlices = arraySize / amountElements + (arraySize % amountElements == 0 ? 0 : 1);
            int lastRow = arraySize % amountElements == 0 ? amountElements : arraySize % amountElements;

            long mean = 0;

            Long[][] array = new Long[numberOfSlices][];

            for (int i = 0; i < numberOfSlices; i++) {
                Long[] count = new Long[i < numberOfSlices - 1 ? amountElements : lastRow];
                for (int j = 0; j < count.length; j++) {
                    line = getHash(line.toCharArray());
                    count[j] = Long.parseLong(line);
                    mean += count[j];
                }
                array[i] = heapSort(count);
            }

            long maxValue = 0;
            long minValue = Long.MAX_VALUE;
            for (int k = 0; k < numberOfSlices; k++) {
                maxValue = maxValue > array[k][0] ? maxValue : array[k][0];
                minValue = minValue < array[k][array[k].length - 1] ? minValue : array[k][array[k].length - 1];
            }

            return "Элементы {" +
                    "Минимальное значение = '" + minValue + '\'' +
                    ", Максимальное значение = '" + maxValue + '\'' +
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