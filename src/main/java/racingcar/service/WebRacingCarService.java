package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.domain.car.Cars;
import racingcar.domain.racinggame.RacingGame;
import racingcar.domain.strategy.move.MoveStrategy;
import racingcar.dto.GameRequestDto;
import racingcar.dto.GameResultDto;
import racingcar.dto.GameResponseDto;

import java.util.List;

@Service
public class WebRacingCarService {
    public GameResponseDto playGame(final GameRequestDto gameRequestDto, final MoveStrategy moveStrategy) {
        final String names = gameRequestDto.getNames();
        final int count = Integer.parseInt(gameRequestDto.getCount());
        final RacingGame racingGame = new RacingGame(names, count);
        
        racingGame.race(moveStrategy);
        return new GameResponseDto(racingGame);
    }
    
    public List<GameResponseDto> findAllGameResult() {
        return null;
    }
}
