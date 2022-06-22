package sv2advfinalexam.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="schools")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="school_name")
    private String schoolName;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "school")
    private List<Student> students = new ArrayList<>();

    public School(String schoolName, Address address) {
        this.schoolName = schoolName;
        this.address = address;
    }
}
