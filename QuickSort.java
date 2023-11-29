import java.util.Random;
// import java.util.*;

public class QuickSort {
    public static void main(String[] args) {
        int arraySize = 500;  // Choose a suitable array size
        int[] arr = new int[arraySize];
        Random random = new Random();

        //Best Case (UnSorted Array)
        for (int i = 0; i < arraySize; i++) {
            arr[i] = random.nextInt(arraySize);
        }
        //System.out.println(Arrays.toString(arr));

        System.out.println("Best Case:");
        // measureTime(arr);
        long startTime = System.nanoTime();
        // quickSortRandom(arr, 0, arr.length - 1);

        quickSortRandom(arr, 0, arr.length - 1);
        // System.out.println(Arrays.toString(arr));

        long endTime = System.nanoTime();
        System.out.println("Time taken: " + (endTime - startTime) + " nanoseconds");
        
        // Worst Case (Sorted Array)
        for (int i = 0; i < arraySize; i++) {
            arr[i] = i;
        }
        System.out.println("\nWorst Case:");
        measureTime(arr);

        // Average Case (Randomly Shuffled Array)
        for (int i = 0; i < arraySize; i++) {
            arr[i] = random.nextInt(arraySize);
        }
        System.out.println("\nAverage Case:");
        measureTime(arr);
    }

    public static void measureTime(int[] arr) {
        long startTime = System.nanoTime();
        quickSort(arr, 0, arr.length - 1);
        long endTime = System.nanoTime();
        System.out.println("Time taken: " + (endTime - startTime) + " nanoseconds");
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low + 1;
        int j = high;

        while (i <= j) {
            while (i <= j && arr[i] <= pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[low];
        arr[low] = arr[j];
        arr[j] = temp;

        return j;
    }

    public static void quickSortRandom(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = Randpartition(arr, low, high);
            quickSortRandom(arr, low, pivotIndex - 1);
            quickSortRandom(arr, pivotIndex + 1, high);
        }
    }

    private static int Randpartition(int[] arr, int low, int high) {
        // Randomly select pivot index
        Random random = new Random();
        int pivotIndex = random.nextInt(high - low + 1) + low;

        // Swap pivot element with the first element
        int pivot = arr[pivotIndex];
        int temp = arr[low];
        arr[low] = pivot;
        arr[pivotIndex] = temp;

        int i = low + 1;
        int j = high;

        while (i <= j) {
            while (i <= j && arr[i] <= pivot) {
                i++;
            }

            while (arr[j] > pivot) {
                j--;
            }

            if (i < j) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap pivot back to its original position
        temp = arr[low];    
        arr[low] = arr[j];
        arr[j] = temp;

        return j;
    }

    public static void quickSortBestCase(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = Randpartition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    public static int middlePivotPartition(int[] arr, int low, int high) {
        // Choose middle element as pivot
        int mid = low + (high - low) / 2;
        int pivot = arr[mid];

        // Swap pivot element with the first element
        int temp = arr[low];
        arr[low] = arr[mid];
        arr[mid] = temp;

        int i = low + 1;
        int j = high;

        while (i <= j) {
            while (i <= j && arr[i] <= pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i < j) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap pivot back to its original position
        temp = arr[low];
        arr[low] = arr[j];
        arr[j] = temp;

        return j;
    }
    
}


// The inconsistent output is likely due to a combination of factors, such as variations in the pivot selection process and the inherent overhead of measuring execution times. For smaller array sizes, these factors can have a more pronounced effect on the observed execution times.

// To ensure consistent results, it's recommended to run the code multiple times and average the execution times for each case. This will help to smooth out any inconsistencies and provide a more accurate representation of the performance differences between the best, worst, and average cases.