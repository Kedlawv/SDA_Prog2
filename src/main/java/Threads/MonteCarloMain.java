package Threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class MonteCarloMain {
    public static int hitCount = 0;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(new MonteCarloMain().monteCarlo(10000, 3));

    }


    public double monteCarlo(long iterations, int numberOfThreads) throws InterruptedException {


        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);

        Runnable sx = () -> {

            for (long i = 0; i < iterations; i++) {
                System.out.println(i);
                double x = Math.random();
                double y = Math.random();
                double r = Math.sqrt(x * x + y * y);
                if (r <= 1) {
                    incHitCount();
                }
            }
        };


        for (int i = 0; i < numberOfThreads; i++) {
            service.execute(sx);
        }

        service.shutdown();
        service.awaitTermination(10, TimeUnit.SECONDS);

        return 4.0 * hitCount / (iterations * numberOfThreads);

    }


    synchronized public static void incHitCount() {
        hitCount++;
    }
}
