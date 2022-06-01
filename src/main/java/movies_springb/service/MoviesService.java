package movies_springb.service;

import movies_springb.dto.MovieDTO;
import movies_springb.dto.commands.CreateMovieCommand;
import movies_springb.dto.commands.UpdateMovieCommand;
import movies_springb.exeptions.MovieNotFoundExeption;
import movies_springb.model.Movie;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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


    public List<MovieDTO> getAllMovies() {
        return movies.stream()
                .map(m -> modelMapper.map(m, MovieDTO.class))
                .collect(Collectors.toList());
    }


    public MovieDTO createMovie(CreateMovieCommand command) {
        Movie createMovie = new Movie(
                idGenerator.incrementAndGet(), command.getTitle(), command.getLenghtMinute());
        movies.add(createMovie);
        return modelMapper.map(createMovie, MovieDTO.class);
    }

    public MovieDTO getMovieById(long id) {
        return modelMapper.map(
                movies.stream()
                        .filter(m -> m.getId() == id)
                        .findFirst()
                        .orElseThrow(() -> new MovieNotFoundExeption("Movie not found with ID: " + id))
                , MovieDTO.class);
    }

    public MovieDTO updateMovieRatings(long id, UpdateMovieCommand command) {
        Movie updateMovie = movies.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElseThrow(() -> new MovieNotFoundExeption("Movie not found with ID: " + id));
        updateMovie.setRatings(command.addRating(command.getRating()));
        return modelMapper.map(updateMovie, MovieDTO.class);
    }

    public List<Integer> getMovieRatingsById(long id) {
        return movies.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .map(m -> modelMapper.map(m.getRatings(), MovieDTO.class))
                .orElseThrow(() -> new MovieNotFoundExeption("Movie not found with ID: " + id))
                .getRatings();
    }
}
