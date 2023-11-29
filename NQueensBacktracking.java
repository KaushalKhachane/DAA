public class NQueensBacktracking {

    public static boolean nQueens(char board[][], int row) {
        // base condition
        if (row == board.length) {
            // Uncomment the line below if you want to print the solutions
            printBoard(board);
            return true;
        }

        for (int j = 0; j < board.length; j++) {
            if (isSafe(board, row, j)) {
                board[row][j] = 'Q';
                if (nQueens(board, row + 1)) {
                    return true;
                }
                board[row][j] = 'X';
            }
        }
        return false;
    }

    public static boolean isSafe(char board[][], int row, int col) {
        // vertically up
        for (int i = row - 1; i >= 0; i--) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // diagonal left up
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // diagonal right up
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    private static void printBoard(char[][] board) {
        System.out.println("------chess board------");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] queenCounts = { 4, 5, 6, 7, 8 };

        for (int queens : queenCounts) {
            char[][] board = new char[queens][queens];

            for (int i = 0; i < queens; i++) {
                for (int j = 0; j < queens; j++) {
                    board[i][j] = 'X';
                }
            }

            long startTime = System.nanoTime();
            boolean solutionExists = nQueens(board, 0);
            long endTime = System.nanoTime();

            System.out.println("For " + queens + " Queens:");
            if (solutionExists) {
                System.out.println("Solution is Possible!!!");
                // Uncomment the line below if you want to print the solutions
                // printBoard(board);
            } else {
                System.out.println("No solution found.");
            }

            System.out.println("Time taken: " + (endTime - startTime) + " nanoseconds\n");
        }
    }
}
