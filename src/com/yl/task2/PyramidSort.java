package com.yl.task2;

import java.util.Comparator;

public class PyramidSort {
    public static Comparator<StrayArr> elementComparison =
            Comparator.comparingLong(StrayArr::getCount);

    public static StrayArr[] heapSort(StrayArr[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
        for (int i = n - 1; i > 0; i--) {
            StrayArr temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
        return arr;
    }

    static void heapify(StrayArr[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < n && elementComparison.compare(arr[l], arr[largest]) > 0)
            largest = l;
        if (r < n && elementComparison.compare(arr[r], arr[largest]) > 0)
            largest = r;
        if (largest != i) {
            StrayArr swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }
}