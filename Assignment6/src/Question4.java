import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Question4 {

    public static void main(String[] args) throws InterruptedException {
        Set<Integer> set = new HashSet<>();

        // First thread modifies the set every second
        Thread thread1 = new Thread(() -> {
            int num = 1;
            while (true) {
                synchronized (set) {
                    set.add(num);
                }
                num++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();

        // Second thread traverses the set every second
        Thread thread2 = new Thread(() -> {
            while (true) {
                synchronized (set) {
                    Iterator<Integer> iterator = set.iterator();
                    while (iterator.hasNext()) {
                        System.out.println(iterator.next());
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.start();

        // Wait for threads to finish
        thread1.join();
        thread2.join();
    }
}
