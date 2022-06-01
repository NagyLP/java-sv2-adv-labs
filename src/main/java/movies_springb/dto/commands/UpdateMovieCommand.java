package movies_springb.dto.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMovieCommand {

    private List<Integer> ratings = new ArrayList<>();
    private int rating;

    public List<Integer> addRating(int rating) {
        ratings.add(rating);
        return ratings;
    }
}
