package spring;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springrestdemomovies.movie.CreateMovieCommand;
import springrestdemomovies.movie.UpdateMovieCommand;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
@AllArgsConstructor
public class MoviesController {

    private MoviesService moviesService;

    @GetMapping
    public List<MovieDto> getAllMovies(@RequestParam Optional<Double> minAvgRating, @RequestParam Optional<Integer> minNumberOfRatings) {
        return moviesService.getAllMovies(minAvgRating,minNumberOfRatings);
    }

    @PostMapping
    public MovieDto createMovie(@RequestBody CreateMovieCommand command) {
        return moviesService.createMovie(command);
    }

    @GetMapping("/{id}")
    public MovieDto getMovieById(@PathVariable("id") long id){
        return moviesService.getMovieById(id);
    }

    @GetMapping("/{id}/ratings")
    public List<RatingDto> getMovieRatingsById(@PathVariable("id") long id){
        return moviesService.getMovieRatingsById(id);
    }

    @PostMapping("/{id}/ratings")
    public List<RatingDto> addMovieRatingById(@PathVariable("id") long id, @RequestBody AddRatingCommand ratingCommand){
        return moviesService.addMovieRatingById(id, ratingCommand);
    }

    @PutMapping("/{id}")
    public MovieDto updateMovieById(@PathVariable("id") long id, @RequestBody UpdateMovieCommand updateMovieCommand){
        return moviesService.updateMovieById(id, updateMovieCommand);
    }



}
