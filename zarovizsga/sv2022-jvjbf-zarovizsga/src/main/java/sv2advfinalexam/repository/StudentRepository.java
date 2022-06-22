package sv2advfinalexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv2advfinalexam.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
}
