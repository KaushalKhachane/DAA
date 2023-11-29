import java.util.Scanner;

public class Knapsack01DP {

    static int knapSack(int W, int wt[], int val[], int n) {
        int i, w;
        int dp[][] = new int[n + 1][W + 1];

        // build table for dp[][] in bottom up manner
        for (i = 0; i <= n; i++) {
            for (w = 0; w <= W; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (wt[i - 1] <= w) {
                    dp[i][w] = Math.max(val[i - 1] + dp[i - 1][w - wt[i - 1]], dp[i - 1][w]);

                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[n][W];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of items:");
        int n = scanner.nextInt();

        int profit[] = new int[n];
        int weight[] = new int[n];

        System.out.println("Enter the profits of the items:");
        for (int i = 0; i < n; i++) {
            profit[i] = scanner.nextInt();
        }

        System.out.println("Enter the weights of the items:");
        for (int i = 0; i < n; i++) {
            weight[i] = scanner.nextInt();
        }

        System.out.println("Enter the maximum weight capacity:");
        int W = scanner.nextInt();

        long startTime = System.nanoTime();
        int result = knapSack(W, weight, profit, n);
        long endTime = System.nanoTime();

        System.out.println("Maximum Profit: " + result);
        System.out.println("Time taken: " + (endTime - startTime) + " nanoseconds");

        scanner.close();
    }
}
