package HomeworkAssignment_3.model;



public class AGV {
    private final int id;
    private final long arrivalTime;
    private long queueEnterTime;
    private long startChargingTime;
    private long endChargingTime;
    private boolean abandoned;

    public AGV(int id, long arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
    }

    public int getId() { return id; }
    public long getArrivalTime() { return arrivalTime; }

    public void markQueueEnter(long time) { this.queueEnterTime = time; }
    public void markStartCharging(long time) { this.startChargingTime = time; }
    public void markEndCharging(long time) { this.endChargingTime = time; }
    public void markAbandoned() { this.abandoned = true; }

    public boolean isAbandoned() { return abandoned; }
    public long getWaitingTime() {
        if (startChargingTime == 0) return -1;
        return startChargingTime - queueEnterTime;
    }

    @Override
    public String toString() {
        return "AGV#" + id +
                (abandoned ? " (left queue)" :
                startChargingTime > 0 ?
                " [charging done]" : " [waiting]");
    }

    public long getEndChargingTime() {
        return this.endChargingTime;
    }

    public long getStartChargingTime() {
        return this.startChargingTime;
    }
}

