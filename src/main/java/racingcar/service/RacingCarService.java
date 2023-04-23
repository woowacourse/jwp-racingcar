package racingcar.service;

import racingcar.controller.dto.GameRequestDtoForPlays;
import racingcar.controller.dto.RacingGameResultDto;
import racingcar.domain.RacingGame;

public class RacingCarService {

    public RacingGameResultDto plays(GameRequestDtoForPlays gameRequestDtoForPlays) {
        RacingGame racingGame = RacingGame.from(gameRequestDtoForPlays);
        racingGame.play();
        return generateRacingGameResultDto(racingGame);
    }

    private RacingGameResultDto generateRacingGameResultDto(RacingGame racingGame) {
        return new RacingGameResultDto(
                racingGame.getCount(),
                racingGame.getCars(),
                racingGame.getWinners()
        );
    }

}
