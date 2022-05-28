package biciklisharing;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BikesRentalService {

    private List<BikeRental> bikeRentals;


    public void readBikeRentalsFromFile() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(
                    "src/main/resources/bikes.csv"));
            for (String item : lines) {
                processLine(item);
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Stop: Don't read file", ioe);
        }
    }

    public List<BikeRental> getAllRentals() {
        createAndReadList();
        return bikeRentals;
    }

    public Set<String> getUserIds() {
        createAndReadList();
        return bikeRentals.stream()
                .map(BikeRental::getUserId)
                .collect(Collectors.toSet());
    }

    private void createAndReadList() {
        if (bikeRentals == null) {
            bikeRentals = new ArrayList<>();
            readBikeRentalsFromFile();
        }
    }

    private void processLine(String item) {
        String[] parts = item.split(";");
        bikeRentals.add(new BikeRental(
                parts[0],
                parts[1],
                LocalDateTime.parse(parts[2]),
                Double.parseDouble(parts[3])));
    }
}
