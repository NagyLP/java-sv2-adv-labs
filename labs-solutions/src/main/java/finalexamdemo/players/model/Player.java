package finalexamdemo.players.model;


import finalexamdemo.teams.model.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "players")
public class Player {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private PlayerPosition playerPosition;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Player(String name, LocalDate dateOfBirth, PlayerPosition playerPosition) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.playerPosition = playerPosition;
    }
}
