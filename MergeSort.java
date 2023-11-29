import java.util.Arrays;
import java.util.Random;

public class MergeSort {
    public static void main(String[] args) {
        int arraySize = 500;
        int[] arr = new int[arraySize];
        Random random = new Random();
        // Best Case (UnSorted Array)
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        reverseArray(arr);
        
        System.out.println("Best Case:");
        long startTime = System.nanoTime();
        mergeSort(arr);
        long endTime = System.nanoTime();
        System.out.println("Time taken: " + (endTime - startTime) + " nanoseconds");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        System.out.println("\nWorst Case:");
        startTime = System.nanoTime();
        mergeSort(arr);
        endTime = System.nanoTime();
        System.out.println("Time taken: " + (endTime - startTime) + " nanoseconds");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1000);
        }
        System.out.println("\nAverage Case:");
        startTime = System.nanoTime();
        mergeSort(arr);
        endTime = System.nanoTime();
        System.out.println("Time taken: " + (endTime - startTime) + " nanoseconds");
    }

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1)
            return;
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    public static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    public static void reverseArray(int[] arr) {
        int arraySize = arr.length;
       
        for(int i = 0; i < arr.length/2; i++){
            int temp = arr[i];
            arr[i] = arr[arraySize - 1 - i];
            arr[arraySize - 1 - i] = temp;
        }
    }
}