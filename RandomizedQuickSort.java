import java.util.Random;

public class RandomizedQuickSort {

    public static void main(String[] args) {
        int arraySize = 500; // Choose a suitable array size
        int[] arr = new int[arraySize];
        Random random = new Random();

        // Initialize the array with random numbers
        for (int i = 0; i < arraySize; i++) {
            arr[i] = random.nextInt(arraySize);
        }

        long startTime = System.nanoTime();
        // Perform Randomized Quick Sort
        quickSort(arr, 0, arr.length - 1);
        long endTime = System.nanoTime();
        long res = endTime-startTime;
        System.out.println("Time taken: "+res);
        // Print the sorted array
        // System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
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
}
