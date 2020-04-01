package model;

import java.time.LocalTime;

public class Schedule {
    public int station_id;
    public int route_id;
    public LocalTime valid_date;
    public LocalTime arrival_time;


    public Schedule(int station_id, int route_id, LocalTime valid_date, LocalTime arrival_time) {
        this.station_id = station_id;
        this.route_id = route_id;
        this.valid_date = valid_date;
        this.arrival_time = arrival_time;
    }
}
