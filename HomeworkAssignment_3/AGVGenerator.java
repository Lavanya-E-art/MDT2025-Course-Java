package HomeworkAssignment_3.simulation;

import HomeworkAssignment_3.model.AGV;
import java.util.Queue;
import java.util.Random;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AGVGenerator implements Runnable {
    private final Queue<AGV> queue;
    private final int totalAGVs;
    private final Random random = new Random();

    public AGVGenerator(Queue<AGV> queue, int totalAGVs) {
        this.queue = queue;
        this.totalAGVs = totalAGVs;
    }

    private String now() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    @Override
    public void run() {
        for (int i = 1; i <= totalAGVs; i++) {
            try {
                Thread.sleep(random.nextInt(4000)+1000); // 1–5s between arrivals
            } catch (InterruptedException e) {
                return;
            }

            AGV agv = new AGV(i, System.currentTimeMillis());
            synchronized (queue) {
                queue.add(agv);
                queue.notifyAll();
            }
            System.out.println("[" + now() + "] 🚙 AGV#" + agv.getId() + " arrived and joined queue.");
        }
    }
}
