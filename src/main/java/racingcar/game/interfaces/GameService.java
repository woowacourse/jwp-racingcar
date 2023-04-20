package racingcar.game.interfaces;


import java.util.List;
import racingcar.game.dto.GameRequestDTO;
import racingcar.game.dto.GameResponseDTO;

public interface GameService {
    
    GameResponseDTO createGame(GameRequestDTO gameRequestDTO);
    
    List<GameResponseDTO> retrieveAllGames();
}