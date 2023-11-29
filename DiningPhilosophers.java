import java.util.concurrent.Semaphore;

public class DiningPhilosophers {

    private static final int NUM_PHILOSOPHERS = 5;

    public static void main(String[] args) {
    // Semaphore: A semaphore is a synchronization primitive that is used to control access to a resource. 
    // In this case, each chopstick is considered a resource, and a semaphore is associated with each one.

    // chopsticks: This is the array that holds the Semaphores representing the chopsticks.
        Semaphore[] chopsticks = new Semaphore[NUM_PHILOSOPHERS];

    // In the context of the Dining Philosophers problem, you can think of this permit as representing the 
    // availability of the associated chopstick. Since each philosopher initially has one chopstick available, 
    // the initial permit count is set to 1.
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            chopsticks[i] = new Semaphore(1);
        }

        Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % NUM_PHILOSOPHERS]);
            new Thread(philosophers[i]).start();
        }
    }
}

class Philosopher implements Runnable {

    private final int id;
    private final Semaphore leftChopstick;
    private final Semaphore rightChopstick;

    public Philosopher(int id, Semaphore leftChopstick, Semaphore rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking.");
        Thread.sleep((long) (Math.random() * 1000));
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " is eating.");
        Thread.sleep((long) (Math.random() * 1000));
    }

    @Override
    public void run() {
        try {
            // This infinite loop represents the continuous cycle of a philosopher thinking, picking up 
            // chopsticks, eating, and releasing chopsticks.
            while (true) {

                // This method represents the philosopher thinking
                think();

                // leftChopstick.acquire();: The philosopher tries to acquire the left chopstick using the 
                // acquire method of the Semaphore (leftChopstick in this case). If the left chopstick is not 
                // available, the philosopher will block until it becomes available.
                leftChopstick.acquire();
                System.out.println("Philosopher " + id + " picked up left chopstick.");

                // Acquire right chopstick
                rightChopstick.acquire();
                System.out.println("Philosopher " + id + " picked up right chopstick.");

                // This method represents the philosopher eating
                eat();

                // After eating, the philosopher releases both the left and right chopsticks using the release
                //  method of the Semaphore. This makes the chopsticks available for other philosophers.
                leftChopstick.release();
                System.out.println("Philosopher " + id + " released left chopstick.");

                // Release chopsticks
                rightChopstick.release();
                System.out.println("Philosopher " + id + " released right chopstick.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
