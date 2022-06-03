package springrestdemomovies.movie.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private Long id;
    private String title;
    private int lengthMinute;
    private List<RatingDTO> ratings = new ArrayList<>();
    private double averageRating;

}
