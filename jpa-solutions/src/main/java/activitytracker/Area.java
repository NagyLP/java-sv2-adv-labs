package activitytracker;

import javax.persistence.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Entity
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    private List<Activity> activities = new LinkedList<>();

    @OneToMany(mappedBy = "area",
               cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Map<String, City> cities = new HashMap<>();

    public Area() {
    }

    public Area(String name) {
        this.name = name;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
        activity.getAreas().add(this);
    }

    public void putCity(City city) {
        cities.put(city.getName(), city);
        city.setArea(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public Map<String, City> getCities() {
        return cities;
    }

    public void setCities(Map<String, City> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "\nArea{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", activities=" + activities +
                '}';
    }
}
