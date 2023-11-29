import java.util.Scanner;

public class MatrixMultiplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of rows for matrix A: ");
        int rowsA = scanner.nextInt();

        System.out.print("Enter the number of columns for matrix A: ");
        int colsA = scanner.nextInt();

        System.out.print("Enter the number of rows for matrix B: ");
        int rowsB = scanner.nextInt();

        System.out.print("Enter the number of columns for matrix B: ");
        int colsB = scanner.nextInt();

        // Check if multiplication is possible
        if (colsA != rowsB) {
            System.out.println("Matrix multiplication is not possible. Number of columns in A must be equal to the number of rows in B.");
            scanner.close();
            return;
        }

        int[][] matrixA = readMatrix(rowsA, colsA, scanner, "A");
        int[][] matrixB = readMatrix(rowsB, colsB, scanner, "B");

        // Sequential multiplication
        long startTimeSequential = System.nanoTime();
        int[][] resultSequential = multiplyMatrixSequential(matrixA, matrixB);
        long endTimeSequential = System.nanoTime();
        System.out.println("Sequential Matrix Multiplication Time: " + (endTimeSequential - startTimeSequential) + " nanoseconds");

        // Multithreaded multiplication
        int numThreads = 4; // You can adjust the number of threads
        long startTimeMultithreaded = System.nanoTime();
        int[][] resultMultithreaded = multiplyMatrixMultithreaded(matrixA, matrixB, numThreads);
        long endTimeMultithreaded = System.nanoTime();
        System.out.println("Multithreaded Matrix Multiplication Time: " + (endTimeMultithreaded - startTimeMultithreaded) + " nanoseconds");

        // Print results
        System.out.println("\nMatrix A:");
        printMatrix(matrixA);

        System.out.println("\nMatrix B:");
        printMatrix(matrixB);

        System.out.println("\nSequential Result:");
        printMatrix(resultSequential);

        System.out.println("\nMultithreaded Result:");
        printMatrix(resultMultithreaded);

        // Check if the results are the same
        System.out.println("\nResults are equal: " + arraysEqual(resultSequential, resultMultithreaded));
    }

    private static int[][] readMatrix(int rows, int cols, Scanner scanner, String name) {
        System.out.println("Enter elements for matrix " + name + ":");
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    private static int[][] multiplyMatrixSequential(int[][] matrixA, int[][] matrixB) {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;

        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }

        return result;
    }

    private static int[][] multiplyMatrixMultithreaded(int[][] matrixA, int[][] matrixB, int numThreads) {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;
  
        int[][] result = new int[rowsA][colsB];
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            final int startRow = i * rowsA / numThreads;
            final int endRow = (i + 1) * rowsA / numThreads;
            

            threads[i] = new Thread(() -> {
                for (int row = startRow; row < endRow; row++) {
                    for (int j = 0; j < colsB; j++) {
                        for (int k = 0; k < colsA; k++) {
                            result[row][j] += matrixA[row][k] * matrixB[k][j];
                        }
                    }
                }
            });

            threads[i].start();
        }

        // Wait for all threads to finish
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static boolean arraysEqual(int[][] array1, int[][] array2) {
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array1[0].length; j++) {
                if (array1[i][j] != array2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
