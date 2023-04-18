package racingcar.service;

import racingcar.controller.dto.RacingGameResponse;
import racingcar.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class RacingGameConsoleService implements RacingGameService {
    @Override
    public RacingGameResponse race(final Cars cars, final int trial) {
        final RacingGame racingGame = new RacingGame(cars, new RandomNumberGenerator());
        racingGame.raceTimesBy(trial);

        final String winners = createWinners(racingGame);
        final List<Car> racingCars = cars.getRacingCars();
        return new RacingGameResponse(winners, racingCars);
    }

    private String createWinners(final RacingGame racingGame) {
        return racingGame.createRacingResult()
                .pickWinner()
                .stream()
                .map(Name::getValue)
                .collect(Collectors.joining(","));
    }

    @Override
    public List<RacingGameResponse> findAllRacingGameHistories() {
        throw new UnsupportedOperationException("[ERROR] 콘솔 레이싱 게임에서는 게임 결과 히스토리를 불러올 수 없습니다.");
    }
}
