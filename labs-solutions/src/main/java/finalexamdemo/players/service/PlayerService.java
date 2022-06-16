package finalexamdemo.players.service;

import finalexamdemo.players.dto.CreateNewPlayerCommand;
import finalexamdemo.players.dto.PlayerDto;
import finalexamdemo.players.exception.PlayerNotFoundException;
import finalexamdemo.players.model.Player;
import finalexamdemo.players.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class PlayerService {

    private PlayerRepository playerRepository;
    private ModelMapper modelMapper;

    public List<PlayerDto> getAllPlayers() {
        List<Player> players = playerRepository.findAll();

        return players.stream()
                .map(p->modelMapper.map(p, PlayerDto.class))
                .collect(Collectors.toList());
    }

    public PlayerDto createNewPlayer(CreateNewPlayerCommand createNewPlayerCommand) {
        Player player = new Player(createNewPlayerCommand.getName(),createNewPlayerCommand.getDateOfBirth(),createNewPlayerCommand.getPlayerPosition());
        playerRepository.save(player);

        return modelMapper.map(player,PlayerDto.class);
    }

    public void deletePlayerById(long id) {
       try{
        playerRepository.deleteById(id);
       } catch (EmptyResultDataAccessException erdae){
           throw new PlayerNotFoundException(id);
       }
    }
}
