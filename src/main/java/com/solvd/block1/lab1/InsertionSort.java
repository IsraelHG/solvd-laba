package com.solvd.block1.lab1;

import java.util.Arrays;
import java.util.Random;

public class InsertionSort {
    // Time complexity: O(n^2)
    // Space complexity: O(1)
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[12];

        // Random integers appended to arr
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }

        System.out.println("Before insertion sort: " + Arrays.toString(arr));
        InsertionSort.insertionSort(arr);
        System.out.println("After insertion sort: " + Arrays.toString(arr));
    }

}
