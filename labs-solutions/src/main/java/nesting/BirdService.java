package nesting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BirdService {

    private BirdDao birdDao;

    public BirdService(BirdDao birdDao) {
        this.birdDao = birdDao;
    }

    public Map<BirdSpecies, Integer> getBirdStatistics() {
        List<Bird> birds = birdDao.listBirds();
        Map<BirdSpecies, Integer> birdStat = new HashMap<>();
        for (Bird bird : birds) {
            if (!birdStat.containsKey(bird.getBirdSpecies())) {
                birdStat.put(bird.getBirdSpecies(), 0);
            }
            birdStat.put(bird.getBirdSpecies(),
                    birdStat.get(bird.getBirdSpecies()) + 1);
        }
        return birdStat;
    }
}

