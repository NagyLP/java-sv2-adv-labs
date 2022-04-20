package locations;

import java.util.Optional;

public class LocationService {

    LocationsRepository locationsRepository;

    public Optional<Double> calculateDistance(String name1, String name2) {
        Optional<Location> oneLocation = locationsRepository.findByName(name1);
        Optional<Location> otherLocation = locationsRepository.findByName(name2);

        if (oneLocation.isEmpty() || oneLocation.isEmpty()) {
            Optional.empty();
        }
        return Optional.of(
                oneLocation.get().distanceFrom(oneLocation.get()));
    }

}
