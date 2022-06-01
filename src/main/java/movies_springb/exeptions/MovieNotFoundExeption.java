package movies_springb.exeptions;

public class MovieNotFoundExeption extends RuntimeException {

    public MovieNotFoundExeption(String message) {
        super(message);
    }
}
