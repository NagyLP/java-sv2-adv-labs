package springrestdemomovies.movie.controller;

import lombok.AllArgsConstructor;
import springrestdemomovies.movie.dtos.RatingDTO;
import springrestdemomovies.movie.dtos.commands.CreateMovieCommand;
import springrestdemomovies.movie.dtos.MovieDTO;
import springrestdemomovies.movie.dtos.commands.PostRatingCommand;
import springrestdemomovies.movie.dtos.commands.UpdateMovieCommand;
import springrestdemomovies.movie.service.MoviesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
@AllArgsConstructor
public class MoviesController {

    private MoviesService moviesService;


    @GetMapping
    public List<MovieDTO> getAllMovies(@RequestParam Optional<Double> minAvgRating, @RequestParam Optional<Integer> minRating) {
        return moviesService.getAllMovies(minAvgRating, minRating);
    }

    @GetMapping("/{id}")
    public MovieDTO getMovieById(@PathVariable("id") long id) {
        return moviesService.getMovieById(id);
    }

    @PostMapping
    public MovieDTO createMovie(@RequestBody CreateMovieCommand createMovieCommand) {
        return moviesService.createMovie(createMovieCommand);
    }

    @GetMapping("/{id}/ratings")
    public List<RatingDTO> getMovieRatingsById(@PathVariable("id") long id) {
        return moviesService.getMovieRatingById(id);
    }

    @PostMapping("/{id}/ratings")
    public List<RatingDTO> addMovieRatingById(
            @PathVariable("id") long id,
            @RequestBody PostRatingCommand postRatingCommand) {
        return moviesService.putMovieRatingById(id, postRatingCommand);
    }

    @PutMapping(value = "/{id}")
    public MovieDTO updateMovie(
            @PathVariable("id") long id,
            @RequestBody UpdateMovieCommand updateMovieCommand) {
        return moviesService.updateMovieById(id, updateMovieCommand);
    }
}
