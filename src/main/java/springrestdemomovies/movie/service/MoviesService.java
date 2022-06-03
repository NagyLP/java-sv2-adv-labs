package springrestdemomovies.movie.service;

import org.springframework.web.bind.annotation.RequestParam;
import springrestdemomovies.movie.dtos.MovieDTO;
import springrestdemomovies.movie.dtos.RatingDTO;
import springrestdemomovies.movie.dtos.commands.CreateMovieCommand;
import springrestdemomovies.movie.dtos.commands.PostRatingCommand;
import springrestdemomovies.movie.dtos.commands.UpdateMovieCommand;
import springrestdemomovies.movie.exeptions.MovieNotFoundExeption;
import springrestdemomovies.movie.model.Movie;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MoviesService {

    private final ModelMapper modelMapper;
    private final AtomicLong idGenerator = new AtomicLong();
    private final List<Movie> movies = new ArrayList<>();


    public MoviesService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public List<MovieDTO> getAllMovies(Optional<Double> minAvgRating, @RequestParam Optional<Integer> minRating) {
        return movies.stream()
                .filter(m -> minAvgRating.isEmpty() || m.getAverageRating() >= minAvgRating.get())
                .filter(m -> minRating.isEmpty() || m.getRatings().size() >= minRating.get())
                .map(m -> modelMapper.map(m, MovieDTO.class))
                .collect(Collectors.toList());
    }


    public MovieDTO createMovie(CreateMovieCommand command) {
        Movie createMovie = new Movie(
                idGenerator.incrementAndGet(), command.getTitle(), command.getLengthMinute());
        movies.add(createMovie);
        return modelMapper.map(createMovie, MovieDTO.class);
    }

    public MovieDTO getMovieById(long id) {
        return modelMapper.map(findMovieById(id), MovieDTO.class);
    }

    public List<RatingDTO> getMovieRatingById(long id) {
        Movie movie = findMovieById(id);
        return movie.getRatings().stream()
                .map(r -> modelMapper.map(r, RatingDTO.class))
                .collect(Collectors.toList());
    }

    public List<RatingDTO> putMovieRatingById(long id, PostRatingCommand ratingCommand) {
        Movie movie = findMovieById(id);
        movie.addRating(ratingCommand.getRating());

        List<RatingDTO> ratings = movie.getRatings().stream()
                .map(r -> modelMapper.map(r, RatingDTO.class))
                .collect(Collectors.toList());
        return ratings;
    }

    public MovieDTO updateMovieById(long id, UpdateMovieCommand updateCommand) {
        Movie movie = findMovieById(id);
        movie.setTitle(updateCommand.getTitle());
        movie.setLengthMinute(updateCommand.getLengthMinute());
        return modelMapper.map(movie, MovieDTO.class);
    }

    private Movie findMovieById(long id) {
        return movies.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElseThrow(() -> new MovieNotFoundExeption("Movie not found with ID: " + id));

    }
}
