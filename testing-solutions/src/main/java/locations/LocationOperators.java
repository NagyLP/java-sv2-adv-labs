package locations;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class LocationOperators {

    public List<Location> filterOnNorth(List<Location> locations) {
        List<Location> resultList = new LinkedList<>();
        for (Location location : locations) {
            if (location.getLatitude() > 0) {
                resultList.add(location);
            }
        }
        return resultList;
//        return locations.stream()
//                .filter(location -> location.getLatitude() > 0)
//                .collect(Collectors.toList());
    }
}
