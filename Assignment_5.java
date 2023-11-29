import java.util.Scanner;

public class Assignment_5 {
    public static void maxProfitMatrix(int m, int profit[], int weight[], int solution[][]) {
//This loop iterates through each object (1 to profit.length), considering 1-based indexing.
        for (int i = 1; i <= profit.length; i++) { // Start from 1, not 0
            //loop iterates through each possible capacity of the knapsack
            for (int j = 0; j <= m; j++) {
                //For each object and each capacity, it calculates the maximum profit by considering whether the current object should be included in the knapsack
                //If the weight of the current object is less than or equal to the current capacity, it compares the profit obtained by including the object or excluding it and chooses the maximum.
                if (j < weight[i - 1]) { // Use i - 1 to access the correct element
                    solution[i][j] = solution[i - 1][j];
                } else {
                    solution[i][j] = Math.max(solution[i - 1][j], solution[i - 1][j - weight[i - 1]] + profit[i - 1]);
                }
            }
        }
    }
    //solution[][]: The matrix obtained from the maxProfitMatrix function.
//object -An array to store whether an object is included (1) or not (0).
    public static void maxProfitObj(int m, int profit[], int weight[], int solution[][], int object[]) {
//It calculates the maximum profit based on the final cell of the solution matrix.
        int max = solution[profit.length][m]; // Use profit.length and m for the final result
        System.out.println("Max profit is " + max);
        int j = m; // Initialize j with the knapsack capacity

        // then iterates from the last object to the first, checking whether each object is included in the optimal solution.
        for (int i = profit.length; i > 0 && max > 0; i--) {

            //If including the current object contributes to the optimal solution,
            // it sets the corresponding element in the object array to 1, updates the max value,
            // and adjusts the capacity j and index i accordingly.
            if (max != solution[i - 1][j]) {
                object[i - 1] = 1;
                max -= profit[i - 1];
                j -= weight[i - 1];
            }
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the total number of objects ");
        int obj_no = sc.nextInt();
        int profit[] = new int[obj_no];
        int weight[] = new int[obj_no];
// Initializes arrays for profits, weights, solution matrix, and object array.
        System.out.println("Enter the max capacity of knapsack ");
        int m = sc.nextInt();

        System.out.println("Enter the weights of each object in ascending order");
        for (int i = 0; i < obj_no; i++) { // Start from 0
            weight[i] = sc.nextInt();
        }

        System.out.println("Enter the profits of each object respectively ");
        for (int i = 0; i < obj_no; i++) { // Start from 0
            profit[i] = sc.nextInt();
        }

        int solution[][] = new int[obj_no + 1][m + 1];
        int object[] = new int[obj_no];

        long startTime = System.nanoTime(); // Record start time in nanoseconds

        //Calls the function to calculate the maximum profit matrix.
        maxProfitMatrix(m, profit, weight, solution);

        System.out.println("The profit matrix is: ");
        for (int i = 0; i <= obj_no; i++) { // Change '<' to '<='
            for (int j = 0; j <= m; j++) {
                System.out.print(solution[i][j] + " ");
            }
            System.out.println();
        }
// Calls the function to find objects that give the maximum profit.
        maxProfitObj(m, profit, weight, solution, object);

        System.out.println("\nThe Objects which give the max profit are ");
        for (int i = 0; i < obj_no; i++) {
            System.out.print("Obj" + (i + 1) + " ");
        }
        System.out.println();
        for (int i = 0; i < obj_no; i++) {
            System.out.print(object[i] + "    ");
        }

        long endTime = System.nanoTime(); // Record end time in nanoseconds
        long actualTime = endTime - startTime;

        System.out.println("\nTime taken: " + actualTime + " nanoseconds");
    }
}