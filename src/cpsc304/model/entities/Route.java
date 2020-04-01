package cpsc304.model.entities;

import java.time.LocalTime;

public class Route {

    public int route_id;
    public LocalTime approx_time;
    public int station_num;

    public Route(int route_id, LocalTime approx_time, int station_num){
        this.route_id = route_id;
        this.approx_time = approx_time;
        this.station_num = station_num;
    }
}
