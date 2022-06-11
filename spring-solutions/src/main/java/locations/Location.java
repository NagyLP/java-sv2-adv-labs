package locations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location_name")
    private String name;

    @Column(name = "loc_lat")
    private double lat;

    @Column(name = "loc_lon")
    private double lon;

    public Location(String name, double lat, double lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

//    @Override
//    public String toString() {
//        return "Location{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", lat=" + lat +
//                ", lon=" + lon +
//                '}';
//    }

    //    public Location(Long id, String name, double lat, double lon) {
//        this.id = id;
//        this.name = name;
//        this.lat = lat;
//        this.lon = lon;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public double getLat() {
//        return lat;
//    }
//
//    public void setLat(double lat) {
//        this.lat = lat;
//    }
//
//    public double getLon() {
//        return lon;
//    }
//
//    public void setLon(double lon) {
//        this.lon = lon;
//    }

}
