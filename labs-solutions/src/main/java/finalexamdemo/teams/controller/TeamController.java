package finalexamdemo.teams.controller;


import finalexamdemo.players.dto.CreateNewPlayerCommand;
import finalexamdemo.teams.dto.CreateTeamCommand;
import finalexamdemo.teams.dto.TeamDto;
import finalexamdemo.teams.dto.UpdateWithExistingPlayerCommand;
import finalexamdemo.teams.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/teams")
@AllArgsConstructor
public class TeamController {

    private TeamService teamService;

    @GetMapping
    public List<TeamDto> getAllTeams(){
        return teamService.getAllTeams();
    }

    @PostMapping
    public TeamDto createTeam(@RequestBody CreateTeamCommand createTeamCommand){
        return teamService.createTeam(createTeamCommand);
    }


    @PostMapping("/{id}/players")
    public TeamDto addNewPlayerToTeam(@PathVariable("id") Long id, @RequestBody CreateNewPlayerCommand createNewPlayerCommand){
        return teamService.addNewPlayerToTeam(id, createNewPlayerCommand);
    }

    @PutMapping("/{id}/players")
    public TeamDto updatePlayersTeam(@PathVariable("id") Long id, @RequestBody UpdateWithExistingPlayerCommand updateWithExistingPlayerCommand){
        return teamService.updateTeamWithExistingPlayer(id, updateWithExistingPlayerCommand);
    }

}
