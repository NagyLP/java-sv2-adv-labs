package Nesting;

import javax.persistence.*;

@Entity
@Table(name = "birds")
public class Bird {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bird_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private BirdSpecies birdSpecies;

    @ManyToOne
    @JoinColumn(name = "nest_id")
    private Nest nest;

    public Bird() {
    }

   public Bird(BirdSpecies birdSpecies) {
        this.birdSpecies = birdSpecies;
    }

    public Bird(BirdSpecies birdSpecies, Nest nest) {
        this.birdSpecies = birdSpecies;
        this.nest = nest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BirdSpecies getBirdSpecies() {
        return birdSpecies;
    }

    public void setBirdSpecies(BirdSpecies birdSpecies) {
        this.birdSpecies = birdSpecies;
    }

    public Nest getNest() {
        return nest;
    }
}
