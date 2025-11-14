package model;

public class TransportTask extends Task {
   
    private Item item;
    private StorageZone sourceZone;
    private StorageZone targetZone;
    
    public TransportTask(int priority,Item item, 
                        StorageZone sourceZone, StorageZone targetZone) {
        super("Transport " + item.getName() + " from " + sourceZone.getZoneName() + 
              " to " + targetZone.getZoneName(), priority);
       
        this.item = item;
        this.sourceZone = sourceZone;
        this.targetZone = targetZone;
    }
    
    @Override
    public void execute() throws Exception {
        AGV agv = this.getAssignedAGV();
        if (agv == null) {
            throw new Exception("No AGV assigned");
        }
        
        // Check battery before starting in case auto charge triggering failed
        if (agv.getBatteryLevel() <= 20) {
            logger.logWarning("AGV-" + agv.getId(), "Battery too low for transport task");
            throw new Exception("AGV-" + agv.getId() + " battery too low. Please charge first!");
        }
        
        logger.logItemTransport(item.getName(), sourceZone.getZoneName(), targetZone.getZoneName());
        System.out.println(" AGV-" + agv.getId() + " starting transport: " + item.getName());
        
        // Move to source zone and load
        System.out.println(" Moving to source: " + sourceZone.getZoneName());
        String startPos = agv.getPosition().getPosition();
        Position sourcePos = new Position(sourceZone.getPosition().getX(), sourceZone.getPosition().getY());
        agv.moveTo(sourcePos);
        logger.logAGVMovement(agv.getId(), startPos, sourceZone.getZoneName());
        
        System.out.println(" Loading item: " + item.getName());
        Thread.sleep(1500);
        sourceZone.removeItem(item);
        
        // Move to destination and unload
        System.out.println(" Moving to destination: " + this.targetZone.getZoneName());
        Position targetPos = new Position(this.targetZone.getPosition().getX(), targetZone.getPosition().getY());
        agv.moveTo(targetPos);
        logger.logAGVMovement(agv.getId(), sourceZone.getZoneName(), targetZone.getZoneName());
        
        System.out.println(" Unloading item: " + this.item.getName());
        Thread.sleep(1500);
        targetZone.addItem(item);
        
        logger.logSystemEvent("Transport Complete", 
            item.getName() + " moved from " + sourceZone.getZoneName() + " to " + targetZone.getZoneName());
        System.out.println(" Transport completed! Item " + item.getName() + 
                         " moved from " + sourceZone.getZoneName() + 
                         " to " + targetZone.getZoneName());
    }
    
    public Item getItem() { return item; }
    public StorageZone getSourceZone() { return sourceZone; }
    public StorageZone getTargetZone() { return targetZone; }
}