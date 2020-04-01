package cpsc304.model.entities;

public class Ferry {
    public int vehicle_id;
    public int capacity;
    public int maxWave;
    public String ferry_num;


    public Ferry(int vehicle_id, int capacity, int maxWave, String ferry_num) {
        this.vehicle_id = vehicle_id;
        this.capacity = capacity;
        this.maxWave = maxWave;
        this.ferry_num = ferry_num;
    }
}
