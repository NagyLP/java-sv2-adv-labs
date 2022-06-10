package locations;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicLong;

public interface LocationsRepository extends JpaRepository<Location, Long> {

//@Repository
//public class LocationsRepository {
//
//    private List<Location> locations = new ArrayList<>();
//    private AtomicLong idGenerator = new AtomicLong();
//
//
//    public List<Location> getLocations(){
//        return new ArrayList<>();}
//
//    public Location findById(long id){
//        return locations.stream()
//                .filter(location -> location.getId() == id)
//                .findFirst()
//                .orElseThrow(()-> )
//    }
}
