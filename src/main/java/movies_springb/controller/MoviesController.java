package movies_springb.controller;

import movies_springb.dto.commands.CreateMovieCommand;
import movies_springb.dto.MovieDTO;
import movies_springb.dto.commands.UpdateMovieCommand;
import movies_springb.service.MoviesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MoviesController {

    private final MoviesService moviesService;

    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @GetMapping
    public List<MovieDTO> getAllMovies() {
        return moviesService.getAllMovies();
    }

    @GetMapping("/{id}")
    public MovieDTO getMovies(@PathVariable("id") long id) {
        return moviesService.getMovieById(id);
    }

    @GetMapping("/{id}/ratings")
    public List<Integer> getRatings(@PathVariable("id") long id) {
        return moviesService.getMovieRatingsById(id);
    }

    @PostMapping
    public MovieDTO createMovie(@RequestBody CreateMovieCommand command) {
        return moviesService.createMovie(command);
    }

    @PutMapping(value = "/{id}/ratings")
    public MovieDTO updateMovie(
            @PathVariable("id") long id,
            @RequestBody UpdateMovieCommand command) {
        return moviesService.updateMovieRatings(id, command);
    }
}
