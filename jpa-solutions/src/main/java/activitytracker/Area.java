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

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "area")
    private Map<String, City> citys = new HashMap<>();

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
        citys.put(city.getName(), city);
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

    public Map<String, City> getCitys() {
        return citys;
    }

    public void setCitys(Map<String, City> citys) {
        this.citys = citys;
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
