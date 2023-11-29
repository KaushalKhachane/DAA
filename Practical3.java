import java.util.Arrays;

public class Practical3 {
    public static void main(String[] args) {
        int[][] initialChromosome = {{0, 10, 15, 20}, {5, 0, 9, 10}, {6, 13, 0, 12}, {8, 8, 9, 0}};
        System.out.println("Initial Chromosome:\n" + Arrays.deepToString(initialChromosome));

        int[][] mutatedChromosome = mutateChromosome(initialChromosome);
        System.out.println("\nMutated Chromosome:\n" + Arrays.deepToString(mutatedChromosome));

        int[] path = calculatePath(mutatedChromosome);
        int cost = calculateCost(mutatedChromosome, path);

        System.out.println("\nPath: " + Arrays.toString(path));
        System.out.println("Cost: " + cost);
    }

    private static int[] calculatePath(int[][] chromosome) {
        int[] path = new int[chromosome.length];
        for (int i = 0; i < chromosome.length; i++) {
            path[i] = findIndexOfMin(chromosome[i]);
        }
        return path;
    }

    private static int findIndexOfMin(int[] array) {
        int minIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    // private static int calculateCost(int[][] chromosome, int[] path) {
    //     int cost = 0;
    //     for (int i = 0; i < chromosome.length; i++) {
    //         int fromCity = path[i];
    //         int toCity = (i + 1) % chromosome.length;  // Wrap around for the last city
    //         cost += chromosome[fromCity][toCity];
    //     }
    //     return cost;
    // }

    private static int calculateCost(int[][] chromosome, int[] path) {
        int cost = 0;
        for (int i = 0; i < chromosome.length; i++) {
            int fromCity = path[i];
            int toCity = path[(i + 1) % chromosome.length];  // Use the order specified in the path array
            cost += chromosome[fromCity][toCity];
            System.out.println("Cost of "+ fromCity+ " to " + toCity+ " is: "+ cost);
        }
        return cost;
    }
    
    

    private static int[][] mutateChromosome(int[][] chromosome) {
        // This line creates a copy of the original chromosome. The reason for copying is that we want to modify the order of cities in the copy without changing the original order.
        // int[][] mutatedChromosome = Arrays.stream(chromosome).map(int[]::clone).toArray(int[][]::new);
        int[][] mutatedChromosome = Arrays.copyOf(chromosome, chromosome.length);

        // The function randomly selects two indices (index1 and index2) in the range of the number of cities in the chromosome. It ensures that index2 is different from index1 to make a meaningful swap.
        int index1 = (int) (Math.random() * chromosome.length);
        int index2;
        
        do {
            index2 = (int) (Math.random() * chromosome.length);
        } while (index1 == index2);

        // This loop goes through each row in the chromosome and swaps the values at index1 and index2. It's like the robot (or the program) deciding to change the order of two cities in its plan for each possible route.
        for (int i = 0; i < chromosome.length; i++) {
            int temp = mutatedChromosome[i][index1];
            mutatedChromosome[i][index1] = mutatedChromosome[i][index2];
            mutatedChromosome[i][index2] = temp;
        }
        // Finally, the function returns the mutated chromosome, which represents a new order of cities for the robot to visit.
        return mutatedChromosome;
    }
}


// This program is like a game for a robot that wants to visit different cities. Each city is like a friend's house, and the robot wants to find the shortest path to visit all of them and come back home.