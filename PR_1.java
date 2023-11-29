import java.util.Arrays;

public class PR_1 {

    // Quicksort implementation
    public static void quicksort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quicksort(arr, low, pivotIndex - 1);
            quicksort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Mergesort implementation
    public static void mergeSort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    private static void merge(int[] arr, int low, int mid, int high) {
        int n1 = mid - low + 1;
        int n2 = high - mid;

        int[] left = Arrays.copyOfRange(arr, low, low + n1);
        int[] right = Arrays.copyOfRange(arr, mid + 1, mid + 1 + n2);

        int i = 0, j = 0, k = low;
        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < n1) {
            arr[k++] = left[i++];
        }

        while (j < n2) {
            arr[k++] = right[j++];
        }
    }

    public static void main(String[] args) {
        // Generate random data for sorting
        int[] data = new int[500];
        for (int i = 0; i < 500; i++) {
            data[i] = (int) (Math.random() * 1000) + 1;
        }

        // Quicksort
        long startTime = System.nanoTime();
        quicksort(data.clone(), 0, data.length - 1);
        long endTime = System.nanoTime();
        System.out.println("Time taken for Quicksort: " + (endTime - startTime) + " seconds");

        // Mergesort
        startTime = System.nanoTime();
        mergeSort(data.clone(), 0, data.length - 1);
        endTime = System.nanoTime();
        System.out.println("Time taken for Mergesort: " + (endTime - startTime) + " seconds");
    }
}