package HomeworkAssignment_3.model;

public class Station {
    private final int id;
    private boolean occupied;

    public Station(int id) {
        this.id = id;
    }

    public synchronized boolean occupy() {
        if (occupied) return false;
        occupied = true;
        return true;
    }

    public synchronized void release() {
        occupied = false;
    }

    public int getId() { return id; }
}

