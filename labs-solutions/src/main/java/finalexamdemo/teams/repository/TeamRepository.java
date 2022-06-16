package finalexamdemo.teams.repository;

import finalexamdemo.teams.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {
}
