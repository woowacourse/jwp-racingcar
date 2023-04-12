package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.domain.RacingGame;
import racingcar.dto.response.RacingGameResponseDto;

@Service
public class RacingCarService {

    public RacingGameResponseDto play(final RacingGame racingGame) {
        while (racingGame.isPlayable()) {
            racingGame.play();
        }
        return RacingGameResponseDto.of(racingGame.findWinners(), racingGame.getCurrentResult());
    }
}
