package springrestdemomovies.movie.dtos.commands;

import lombok.Data;

@Data
public class UpdateMovieCommand {

    private String title;
    private int lengthMinute;
}
