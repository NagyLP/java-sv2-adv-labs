package football_teams;

public class TeamPlayerService {

    private TeamRepository teamRepository;
    private PlayerRepository playerRepository;

    public TeamPlayerService(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    public void transferPlayer(long teamId, long playerId) {
        Team team = teamRepository.fetchTeamById(teamId);
        Player player = playerRepository.findById(playerId);

        if (player.getTeam() == null) {
            playerRepository.updatePlayerTeam(playerId, teamId);

        } else if (player.getPrice() < (team.getBudget() * 20.0) / 100.0) {
            playerRepository.updatePlayerTeam(playerId, teamId);
            teamRepository.updateBudget(teamId,
                    team.getBudget() - player.getPrice());

        } else {
            throw new IllegalArgumentException("Cannot transfer player, Team budget: "
                    + team.getBudget());
        }
    }
}
