package HomeworkAssignment_3.core;


import java.util.concurrent.atomic.AtomicInteger;

public class StatisticsCollector {
    private final AtomicInteger totalServed = new AtomicInteger();
    private final AtomicInteger totalAbandoned = new AtomicInteger();

    public void addServed(int count) { totalServed.addAndGet(count); }
    public void addAbandoned(int count) { totalAbandoned.addAndGet(count); }

    public void printSummary() {
        System.out.println("========== Simulation Summary ==========");
        System.out.println("AGVs Served: " + totalServed);
        System.out.println("AGVs Abandoned: " + totalAbandoned);
        System.out.println("========================================");
    }
}
