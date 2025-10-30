package Resources;

import java.util.ArrayList;
import java.util.List;

import Processes.Process;

public class Warehouse {
    private List<Process> processes = new ArrayList<>();

    public Warehouse(String name) { }

    public void addProcess(Process p) { processes.add(p); }
    public List<Process> getProcesses() { return processes; }

    /** Simulate running all processes once and produce a summary */
    public Summary simulateOnce() {
        Summary s = new Summary();
        for (Process p : processes) {
            double opCost = p.getOperationsCost();
            double total = p.getTotalCost();
            s.totalOperationCost += opCost;
            s.totalProcessCost += total;
            s.processSummaries.add(new ProcessSummary(p.getId(), opCost, total));
        }
        return s;
    }

    public static class Summary {
        public double totalOperationCost = 0.0;
        public double totalProcessCost = 0.0;
        public List<ProcessSummary> processSummaries = new ArrayList<>();
    }

    public static class ProcessSummary {
        public String processId;
        public double operationsCost;
        public double totalCost;
        public ProcessSummary(String id, double opCost, double total) {
            this.processId = id;
            this.operationsCost = opCost;
            this.totalCost = total;
        }
    }
}
