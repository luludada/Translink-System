package cpsc304.model.entities;

public class Port {
    public int station_id;
    public int dock_num;
    public String port_name;

    public Port(int station_id, int dock_num, String port_name) {
        this.station_id = station_id;
        this.dock_num = dock_num;
        this.port_name = port_name;
    }

}
