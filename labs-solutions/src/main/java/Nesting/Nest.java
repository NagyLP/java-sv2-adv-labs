package Nesting;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "nests")
@Inheritance(strategy = InheritanceType.JOINED)
public class Nest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "number_of_eggs")
    private int numberOfEggs;

    @OneToMany(mappedBy = "nest")
    private Set<Bird> bird = new TreeSet<>();

    public Nest() {
    }

    public Nest(int numberOfEggs) {
        this.numberOfEggs = numberOfEggs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberOfEggs() {
        return numberOfEggs;
    }

    public void setNumberOfEggs(int numberOfEggs) {
        this.numberOfEggs = numberOfEggs;
    }

    public Set<Bird> getBird() {
        return bird;
    }
}
