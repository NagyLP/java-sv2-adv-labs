package movies;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(name = "dat_of_release")
    private LocalDate releaseDate;
    private int length;

    public Movie() {
    }

    public Movie(String title, LocalDate releaseDate, int length) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.length = length;
    }

    public Movie(Long id, String title, LocalDate releaseDate, int length) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.length = length;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", length=" + length +
                '}';
    }
}