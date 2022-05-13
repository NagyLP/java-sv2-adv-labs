package football_teams;

public class TeamPlayerService {

    private static final double MINIMUM_BUDGET_PERCENT = 20;

    private TeamRepository teamRepository;
    private PlayerRepository playerRepository;

    public TeamPlayerService(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    public void transferPlayer(long teamId, long playerId) {
        Team team = teamRepository.fetchTeamById(teamId);
        Player player = playerRepository.findPlayerById(playerId);
        if (player.getTeam() == null) {
            playerRepository.updatePlayerTeam(playerId, teamId);
        } else if (hasEnoughtBudget(team, player)) {
            financialOperations(teamId, playerId, team, player);
        } else {
            throw new NotEnoughBudgetException("Cannot transfer player, Team budget: "
                    + team.getBudget());
        }
    }

    private void financialOperations(long teamId, long playerId, Team team, Player player) {
        Team originalTeam = player.getTeam();
        if (originalTeam != null) {
            teamRepository.updateBudget(originalTeam.getId(),
                    originalTeam.getBudget() + player.getPrice());
        }
        playerRepository.updatePlayerTeam(playerId, teamId);
        teamRepository.updateBudget(teamId,
                team.getBudget() - player.getPrice());
    }

    private boolean hasEnoughtBudget(Team team, Player player) {
        return player.getPrice()
                < team.getBudget() * MINIMUM_BUDGET_PERCENT / 100.0;
    }
}
