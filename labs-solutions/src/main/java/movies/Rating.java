package movies;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Embeddable
//@Entity
//@Table(name = "ratings")
public class Rating {

    private double value;
    private String username;

    public Rating() {
    }

    public Rating(double rating, String username) {
        this.value = rating;
        this.username = username;
    }

    public double getValue() {
        return value;
    }

    public String getUsername() {
        return username;
    }

    public void setValue(double rating) {
        this.value = rating;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
