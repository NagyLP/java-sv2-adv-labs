package finalexamdemo.players.controller;


import finalexamdemo.players.dto.CreateNewPlayerCommand;
import finalexamdemo.players.dto.PlayerDto;
import finalexamdemo.players.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/players")
@AllArgsConstructor
public class PlayerController {

    private PlayerService playerService;


    @GetMapping
    public List<PlayerDto> getAllPlayers(){
        return playerService.getAllPlayers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerDto createNewPlayer(@RequestBody CreateNewPlayerCommand createNewPlayerCommand){
        return playerService.createNewPlayer(createNewPlayerCommand);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlayerById(@PathVariable("id") long id){
        playerService.deletePlayerById(id);
    }


}
