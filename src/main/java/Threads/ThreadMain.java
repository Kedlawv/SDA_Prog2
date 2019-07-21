package Threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



public class ThreadMain {

    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new SimpleThread());
        thread.start();
        //Thread.sleep(2);
        thread.sleep(2);
        System.out.println("Thread started");

        ExecutorService service = Executors.newFixedThreadPool(2);


        service.execute(() -> {
            System.out.println("\nService.execute1");
            for(int i = 0; i < 1000000;i++) {
                if(i%12 == 0) {
                    System.out.println();
                }
                incCounter();
                System.out.print("sx1_" + i + " ");
            }
        });

        service.execute(() -> {
            System.out.println("\nService.execute2");
            for(int i = 0; i < 1000000;i++) {
                if(i%12 == 0) {
                    System.out.println();
                }
                incCounter();
                System.out.print("sx2_" + i + " ");
            }
        });

        System.out.println("\ncounter:" + counter);

        service.shutdown();
        service.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("\ncounter:" + counter);


    }

    synchronized public static void incCounter() {
        counter++;
    }



}
