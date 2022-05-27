package biciklisharing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;

public class BikesRentalService {

    private List<BikeRental> bikeRentals = new ArrayList<>();

    public BikesRentalService(List<BikeRental> bikeRentals) {
        this.bikeRentals = bikeRentals;
    }

    public void readFromFile(Path path) {
        try {
            List<String> lines = Files.readAllLines(path);
            for (String item : lines) {
                processLine(item);
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Error: Don't read file", ioe);
        }
    }

    private void processLine(String item) {
        String[] parts = item.split(";");
        bikeRentals.add(new BikeRental(parts[0],
                parts[1],
                LocalDateTime.parse(parts[2]),
                Double.parseDouble(parts[3])));
        }
    }
