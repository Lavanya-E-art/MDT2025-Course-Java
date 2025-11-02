package HomeworkAssignment_3;

import HomeworkAssignment_3.simulation.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
  
        ChargingSimulation sim = new ChargingSimulation();
        sim.run();

        // Simulate M tasks with K AGVs
        int M = 10; // tasks
        int K = 3;  // available AGVs
        simulateParallelTasks(M, K);
    }

    private static void simulateParallelTasks(int M, int K) throws InterruptedException {
        Semaphore agvPool = new Semaphore(K);
        ExecutorService executor = Executors.newFixedThreadPool(M);
        CountDownLatch latch = new CountDownLatch(M);

        System.out.println("\n--- Running " + M + " parallel tasks with " + K + " AGVs ---");

        for (int i = 1; i <= M; i++) {
            final int taskId = i;
            executor.submit(() -> {
                try {
                    agvPool.acquire();
                    System.out.println("Task#" + taskId + " acquired AGV.");
                    TimeUnit.SECONDS.sleep(3 + (int)(Math.random() * 4));
                    System.out.println("Task#" + taskId + " released AGV.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    agvPool.release();
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();
        System.out.println("--- All tasks completed ---");
    }
}

