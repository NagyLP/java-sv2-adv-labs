package locations;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LocationReader {

    public List<Location> readLocation(Path path) {
        try {
            return Files.readAllLines(path)
                    .stream()
                    .map(LocationParser::parse)
                    .toList();
        } catch (Exception ex) {
            throw new IllegalStateException("Can't read file", ex);
        }
    }
}
