package spring;

import lombok.*;
import springrestdemomovies.movie.Rating;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Movie {

    private Long id;
    private String title;
    private int length;
    private List<Rating> ratings = new ArrayList<>();
    private double averageRating;

    public Movie(Long id, String title, int length) {
        this.id = id;
        this.title = title;
        this.length = length;
    }

    public void addRating(int rating){
        ratings.add(new Rating(rating));
        calculateAverageRating();
    }

    private void calculateAverageRating() {
        averageRating = ratings.stream().mapToInt(i->i.getValue()).average().orElseThrow(()->new IllegalStateException("List is empty!"));
    }

}
