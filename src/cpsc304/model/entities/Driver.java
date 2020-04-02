package cpsc304.model.entities;

public class Driver {
    public int vehicle_id;
    public int capacity;
    public int route_id;
    public int SIN;
    public String phone;
    public String name;
    public String license_ID;

    public Driver(int vehicle_id, int capacity, int route_id, int SIN, String phone, String name, String license_ID) {
        this.vehicle_id = vehicle_id;
        this.capacity = capacity;
        this.route_id = route_id;
        this.SIN = SIN;
        this.name = name;
        this.license_ID = license_ID;
        this.phone = phone;
    }

}
