package football_teams;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
@NamedQuery(name = "fetchTeamsByCountryNLeague", query =
        "SELECT t FROM Team t" +
                " WHERE t.country = :country AND t.league = :league ORDER BY t.score DESC")
// Gondolom, ha több szálon, több csomagban/szekvenciában? visz. egyidőben érkezik kéréstömegek, akkor tán így gyorsabb elérésű, és "szétoszthatóbb" lehet.
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(name = "team_league")
    private League league;
    private int score;
    //    @ElementCollection
//    @CollectionTable(name = "players", joinColumns = @JoinColumn(name = "team_id"))
//    @Column(name = "player_name")
//    @OrderColumn(name = "player_")

    @OneToMany(mappedBy = "team", cascade = CascadeType.PERSIST)
    private List<Player> players = new ArrayList<>();

    private int points;
    private int budget;

    public Team() {
    }

    public Team(String name, String country, League league, int budget) {
        this.name = name;
        this.country = country;
        this.league = league;
        this.budget = budget;
    }


    public void addPlayers(Player player) {
        players.add(player);
        player.setTeam(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
}
