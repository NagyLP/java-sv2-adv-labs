package movies;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String title;
    @Column(name = "dat_of_release")
    private LocalDate releaseDate;
    private int length;

    @ElementCollection
    @CollectionTable(name = "ratings", joinColumns = @JoinColumn(name = "movie_id"))
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings = new LinkedList<>();

    public Movie() {
    }

    public Movie(String title, LocalDate releaseDate, int length) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.length = length;
    }

    public Movie(Long id, String title, LocalDate releaseDate, int length) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.releaseDate = releaseDate;
        this.length = length;
    }

    public void addRating(Rating rating) {
        ratings.add(rating);
    }

    public void setId(Long id) {
        this.id = id.toString();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public int getLength() {
        return length;
    }

    public String getId() {
        return id;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return "\nMovie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", length=" + length +
                '}';
    }
}