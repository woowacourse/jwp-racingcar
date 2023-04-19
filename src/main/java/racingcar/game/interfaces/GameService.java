package racingcar.game.interfaces;


import racingcar.game.dto.GameRequestDTO;
import racingcar.game.dto.GameResponseDTO;

public interface GameService {
    
    GameResponseDTO createGame(GameRequestDTO gameRequestDTO);
}