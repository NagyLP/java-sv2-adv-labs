package finalexamdemo.teams.service;


import finalexamdemo.players.dto.CreateNewPlayerCommand;
import finalexamdemo.players.exception.PlayerNotFoundException;
import finalexamdemo.players.model.Player;
import finalexamdemo.players.model.PlayerPosition;
import finalexamdemo.players.repository.PlayerRepository;
import finalexamdemo.teams.dto.CreateTeamCommand;
import finalexamdemo.teams.dto.TeamDto;
import finalexamdemo.teams.dto.UpdateWithExistingPlayerCommand;
import finalexamdemo.teams.exceptions.TeamNotFoundException;
import finalexamdemo.teams.model.Team;
import finalexamdemo.teams.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeamService {
    
    private PlayerRepository playerRepository;
    private TeamRepository teamRepository;
    private ModelMapper modelMapper;

    public List<TeamDto> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        return teams.stream()
                .map(t->modelMapper.map(t,TeamDto.class)).collect(Collectors.toList());
    }

    public TeamDto createTeam(CreateTeamCommand createTeamCommand) {
        List<Player> players = createTeamCommand.getPlayers().stream().map(p->modelMapper.map(p, Player.class)).collect(Collectors.toList());

        Team team = new Team(createTeamCommand.getName(),players);
        players.stream().forEach(p->p.setTeam(team));
        teamRepository.save(team);

        return modelMapper.map(team, TeamDto.class);
    }

    public TeamDto addNewPlayerToTeam(Long id, CreateNewPlayerCommand createNewPlayerCommand) {
        Team team = teamRepository.findById(id).orElseThrow(()->new TeamNotFoundException(id));
        Player player = new Player(createNewPlayerCommand.getName(),createNewPlayerCommand.getDateOfBirth(),createNewPlayerCommand.getPlayerPosition());
        player.setTeam(team);

        playerRepository.save(player);

        return modelMapper.map(team, TeamDto.class);
    }

    @Transactional
    public TeamDto updateTeamWithExistingPlayer(Long id, UpdateWithExistingPlayerCommand updateWithExistingPlayerCommand) {
        Player player = playerRepository.findById(updateWithExistingPlayerCommand.getPlayerId()).orElseThrow(()-> new PlayerNotFoundException(updateWithExistingPlayerCommand.getPlayerId()));
        Team team = teamRepository.findById(id).orElseThrow(()->new TeamNotFoundException(id));

        checkTransferIsLegal(player,team);

        team.addPlayer(player);

        return modelMapper.map(team,TeamDto.class);

    }

    private void checkTransferIsLegal(Player player, Team team) {
        if(player.getTeam() != null){
            throw new IllegalArgumentException("Player has a team!");
        }
        if(checkNumberOfPosition(team,player.getPlayerPosition())>2){
            throw new IllegalArgumentException("Team has more than 2 players on that position!");
        }
    }

    private int checkNumberOfPosition(Team team, PlayerPosition playerPosition) {

        return (int) team.getPlayers().stream()
                .filter(p->p.getPlayerPosition()==playerPosition)
                .count();

    }
}
