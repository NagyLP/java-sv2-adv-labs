package locations;

import java.util.Optional;

public class LocationService {

    LocationsRepository locationsRepository;

    public LocationService(LocationsRepository locationsRepository) {
        this.locationsRepository = locationsRepository;
    }

    public Optional<Double> calculateDistance(String name1, String name2) {
        Optional<Location> oneLocation = locationsRepository.findByName(name1);
        Optional<Location> otherLocation = locationsRepository.findByName(name2);

        if (oneLocation.isEmpty() || otherLocation.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(
                oneLocation.get().distanceFrom(otherLocation.get()));
    }

    public boolean isOnNorthernHemisphere(String name) {
        return locationsRepository.findLatitudeByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Location"))
                > 0;


    }
}
