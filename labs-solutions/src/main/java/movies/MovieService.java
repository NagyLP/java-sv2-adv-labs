package movies;

import java.time.LocalDate;
import java.util.Optional;

public class MovieService {

    private MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }


    public Movie saveMovie(String title, LocalDate releaseDate, int length){
        if(!checkDate(releaseDate)){
            throw new IllegalArgumentException("Date is not correct: "+releaseDate);
        }
        return repository.saveMovie(new Movie(title,releaseDate,length));
    }

    private boolean checkDate(LocalDate date){
        return date.isAfter(LocalDate.of(1911,1,1));
    }


}