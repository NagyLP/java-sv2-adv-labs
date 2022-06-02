package movies_springb.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private Long id;
    private String title;
    private int lenghtMinute;
    private List<Integer> ratings = new ArrayList<>();
    private double averageRating;

    public Movie(Long id, String title, int lenghtMinute) {
        this.id = id;
        this.title = title;
        this.lenghtMinute = lenghtMinute;
    }

    public Movie(List<Integer> ratings, double averageRating) {
        this.ratings = ratings;
        this.averageRating = averageRating;
    }
}
