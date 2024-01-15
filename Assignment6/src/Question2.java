import java.util.concurrent.atomic.AtomicInteger;

public class Question2 {

    // Without synchronization
    private static int sumWithoutSync = 0;

    // With synchronization
    private static int sumWithSync = 0;
    private static final Object lock = new Object();

    // Atomic variable 
    private static AtomicInteger atomicSum = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        int numThreads = 1000;

        // Without synchronization
        Thread[] threadsWithoutSync = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threadsWithoutSync[i] = new Thread(() -> {
                sumWithoutSync += 1;
            });
            threadsWithoutSync[i].start();
        }
        for (Thread thread : threadsWithoutSync) {
            thread.join();
        }
        System.out.println("Sum without synchronization: " + sumWithoutSync);

        // With synchronization
        Thread[] threadsWithSync = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threadsWithSync[i] = new Thread(() -> {
                synchronized (lock) {
                    sumWithSync += 1;
                }
            });
            threadsWithSync[i].start();
        }
        for (Thread thread : threadsWithSync) {
            thread.join();
        }
        System.out.println("Sum with synchronization: " + sumWithSync);

        // Atomic variable
        Thread[] threadsAtomic = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threadsAtomic[i] = new Thread(() -> {
                atomicSum.incrementAndGet();
            });
            threadsAtomic[i].start();
        }
        for (Thread thread : threadsAtomic) {
            thread.join();
        }
        System.out.println("Sum with atomic variable: " + atomicSum.get());
    }
}
