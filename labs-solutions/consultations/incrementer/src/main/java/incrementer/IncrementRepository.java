package incrementer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@Repository
public class IncrementRepository {

    @Getter
    @Setter
    private int counter;

}
