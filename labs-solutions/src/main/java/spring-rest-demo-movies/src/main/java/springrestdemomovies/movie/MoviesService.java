package spring;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import springrestdemomovies.movie.AddRatingCommand;
import springrestdemomovies.movie.CreateMovieCommand;
import springrestdemomovies.movie.MovieNotFoundException;
import springrestdemomovies.movie.UpdateMovieCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MoviesService {

    private AtomicLong idGenerator = new AtomicLong();
    private List<Movie> movies = new ArrayList<>();

    private ModelMapper modelMapper;

    public MoviesService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<MovieDto> getAllMovies(Optional<Double> minAverageRating, Optional<Integer> minNumberOfRatings) {
        return movies.stream()
                .filter(m->minAverageRating.isEmpty() || m.getAverageRating()>=minAverageRating.get())
                .filter(m->minNumberOfRatings.isEmpty()|| m.getRatings().size()>=minNumberOfRatings.get())
                .map(m->modelMapper.map(m, MovieDto.class))
                .collect(Collectors.toList());
    }

    public MovieDto createMovie(CreateMovieCommand command) {
        Movie movie = new Movie(idGenerator.incrementAndGet(),command.getTitle(),command.getLength());
        movies.add(movie);
        return modelMapper.map(movie,MovieDto.class);
    }

    public MovieDto getMovieById(long id) {
        Movie movie = findMovieById(id);
        return modelMapper.map(movie,MovieDto.class);

    }

    private Movie findMovieById(long id){
        return movies.stream()
                .filter(m->m.getId()==id)
                .findFirst().orElseThrow(()->new MovieNotFoundException("Movie not found with id:"+id));

    }


    public List<RatingDto> getMovieRatingsById(long id) {
        Movie movie = findMovieById(id);
        List<RatingDto> ratings = movie.getRatings().stream()
                .map(r->modelMapper.map(r,RatingDto.class))
                .collect(Collectors.toList());
        return ratings;

    }

    public List<RatingDto> addMovieRatingById(long id, AddRatingCommand ratingCommand) {
        Movie movie = findMovieById(id);
        movie.addRating(ratingCommand.getRating());

        List<RatingDto> ratings = movie.getRatings().stream()
                .map(r->modelMapper.map(r,RatingDto.class))
                .collect(Collectors.toList());
        return ratings;
    }

    public MovieDto updateMovieById(long id, UpdateMovieCommand updateMovieCommand) {
        Movie movie = findMovieById(id);
        movie.setTitle(updateMovieCommand.getTitle());
        movie.setLength(updateMovieCommand.getLength());

        return modelMapper.map(movie,MovieDto.class);

    }
}
