package sv2advfinalexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sv2advfinalexam.dto.SchoolDto;
import sv2advfinalexam.model.School;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School,Long> {

    @Query("select s from School s where :city is null or s.address.city = :city")
    List<School> findAllByCity(Optional<String> city);
}
