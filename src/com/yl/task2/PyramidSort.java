package com.yl.task2;

import java.util.Comparator;

public class PyramidSort implements Comparator<Integer> {
    @Override
    public int compare(Integer comparedElements, Integer supportElements) {
        return comparedElements < supportElements ? 1 : 0;
    }

    public static Integer[] heapSort(Integer[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
        for (int i = n - 1; i > 0; i--) {
            Integer temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
        return arr;
    }

    static void heapify(Integer[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        PyramidSort pyramidSort = new PyramidSort();
        if (l < n && pyramidSort.compare(arr[l], arr[largest]) > 0)
            largest = l;
        if (r < n && pyramidSort.compare(arr[r], arr[largest]) > 0)
            largest = r;
        if (largest != i) {
            Integer swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }
}