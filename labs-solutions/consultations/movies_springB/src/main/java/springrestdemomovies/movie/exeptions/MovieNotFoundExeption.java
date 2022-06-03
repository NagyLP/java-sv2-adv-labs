package springrestdemomovies.movie.exeptions;

public class MovieNotFoundExeption extends RuntimeException {

    public MovieNotFoundExeption(String message) {
        super(message);
    }
}
