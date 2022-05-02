package football_teams;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;
    private String country;
    @Enumerated(value = EnumType.STRING)
    private League league;
    private int score;
    @ElementCollection
    @CollectionTable(name = "players", joinColumns = @JoinColumn(name = "team_id"))
    @Column(name = "player_name")
    @OrderColumn(name = "player_")
    private List<String> players = new ArrayList<>();

    public Team() {
    }

    public Team(String name, String country, League league, int score) {
        this.name = name;
        this.country = country;
        this.league = league;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public League getLeague() {
        return league;
    }

    public int getScore() {

        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public void addPlayers(String player){
        players.add(player);
    }
}
