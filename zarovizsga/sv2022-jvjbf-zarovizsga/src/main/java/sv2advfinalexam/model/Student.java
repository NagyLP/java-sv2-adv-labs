package sv2advfinalexam.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name ="students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "date_of_birt")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "school_age_status")
    private SchoolAgeStatus schoolAgeStatus;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    public Student(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public long calculateAge(){
        return ChronoUnit.YEARS.between(dateOfBirth,LocalDate.now());
    }
}
