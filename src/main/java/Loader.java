import java.util.HashSet;
import java.util.Set;

public class Loader {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        Set<Thread> threads = new HashSet<>();

        Thread thread = new Thread(new Generator(1, 49, "res/numbers1.txt"));
        Thread thread1 = new Thread(new Generator(50, 99, "res/numbers2.txt"));
        Thread thread2 = new Thread(new Generator(100, 149, "res/numbers3.txt"));
        Thread thread3 = new Thread(new Generator(150, 199, "res/numbers4.txt"));
        threads.add(thread);
        threads.add(thread1);
        threads.add(thread2);
        threads.add(thread3);
        threads.forEach(Thread::start);
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }
}
