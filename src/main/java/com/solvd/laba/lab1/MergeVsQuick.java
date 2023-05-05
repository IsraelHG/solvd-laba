package com.solvd.laba.lab1;

import java.util.Arrays;
import java.util.Random;

/**
 * The Sorting class provides static methods for
 * sorting an array of integers using
 * Merge Sort and Quick Sort.
 */
class Sorting {

    /**
     * Sorts the given array of integers in ascending order.
     *
     * @param arr   the array of integers
     * @param left  the starting index
     * @param right the ending index
     */
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    /**
     * Merges two sorted subarrays of the given array into a single sorted array.
     *
     * @param arr   the array of integers
     * @param left  the starting index
     * @param mid   the middle index
     * @param right the ending index
     */
    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k] = arr[i];
                i++;
            } else {
                temp[k] = arr[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
            temp[k] = arr[i];
            i++;
            k++;
        }
        while (j <= right) {
            temp[k] = arr[j];
            j++;
            k++;
        }
        for (k = 0; k < temp.length; k++) {
            arr[left + k] = temp[k];
        }
    }

    /**
     * Sorts the given array of integers in ascending order.
     *
     * @param arr   the array of integers
     * @param left  the starting index
     * @param right the ending index
     */
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
    }

    /**
     * Partitions the given array of integers around a pivot element.
     *
     * @param arr   the array of integers
     * @param left  the starting index
     * @param right the ending index
     * @return the index of the pivot element after partitioning
     */
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = temp;
        return i + 1;
    }
}

public class MergeVsQuick {
    public static void main(String[] args) {

        Random random = new Random();
        int[] arr = new int[5000000];

        // Speed testing both sorting algorithms 5 times
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < arr.length; j++) {
                arr[j] = random.nextInt(5000000);
            }
            int[] arrSort1 = Arrays.copyOf(arr, arr.length);
            int[] arrSort2 = Arrays.copyOf(arr, arr.length);

            long startTime, endTime;

            System.out.println("-------------------SPEED TEST #" + (i + 1) + "-------------------");
            startTime = System.currentTimeMillis();
            Sorting.mergeSort(arrSort1, 0, arrSort1.length - 1);
            endTime = System.currentTimeMillis();
            System.out.println("--> Merge sort: " + (endTime - startTime) + " ms");

            startTime = System.currentTimeMillis();
            Sorting.quickSort(arrSort2, 0, arrSort2.length - 1);
            endTime = System.currentTimeMillis();
            System.out.println("--> Quick sort: " + (endTime - startTime) + " ms");
            System.out.println("---------------------------------------------------\n");
        }
    }
}
