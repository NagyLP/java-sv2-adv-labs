package springrestdemomovies.movie.dtos.commands;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateMovieCommand {

    private String title;
    private int lengthMinute;
}
