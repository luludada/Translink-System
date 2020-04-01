package model;

import java.time.LocalTime;

public class Stop_At {
    public int vehicle_id;
    public int station_id;
    public LocalTime time;

    public Stop_At(int vehicle_id, int station_id, LocalTime time) {
        this.vehicle_id = vehicle_id;
        this.station_id = station_id;
        this.time = time;
    }
}
