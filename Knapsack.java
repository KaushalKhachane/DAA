import java.util.Scanner;

public class Knapsack {

    private static int knapsack(int[] values, int[] weights, int capacity) {
        int n = values.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Find the items that were selected
        int maxValue = dp[n][capacity];
        int w = capacity;
        int[] selectedItems = new int[n];
        for (int i = n; i > 0 && maxValue > 0; i--) {
            if (maxValue != dp[i - 1][w]) {
                selectedItems[i - 1] = 1;
                maxValue -= values[i - 1];
                w -= weights[i - 1];
            }
        }

        System.out.println("Selected items for the knapsack:");
        for (int i = 0; i < n; i++) {
            if (selectedItems[i] == 1) {
                System.out.println("Item " + (i + 1) + ": Profit = " + values[i] + ", Weight = " + weights[i]);
            }
        }

        return dp[n][capacity];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();

        int[] values = new int[n];
        int[] weights = new int[n];

        System.out.println("Enter the values and weights for each item:");
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }

        System.out.print("Enter the knapsack capacity: ");
        int capacity = scanner.nextInt();

        long startTime = System.nanoTime();

        int maxValue = knapsack(values, weights, capacity);

        long endTime = System.nanoTime();
        long timeComplexity = endTime - startTime;

        System.out.println("Maximum value that can be obtained: " + maxValue);
        System.out.println("Time Complexity: " + timeComplexity + " nanoseconds");

        // Close the scanner
        scanner.close();
    }
}
/*
 * 
10 2
15 3
40 5
30 4
90 6
5  8
*/