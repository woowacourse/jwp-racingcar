package racingcar.service;

import racingcar.domain.Cars;
import racingcar.domain.Lap;
import racingcar.domain.NumberGenerator;
import racingcar.domain.WinnerMaker;
import racingcar.service.dto.GameResponseDto;
import racingcar.util.ListJoiner;

import java.util.List;

public class GameRunner {

    public static GameResponseDto race(final Cars cars, final Lap lap, final NumberGenerator numberGenerator) {
        while (!lap.isFinish()) {
            cars.moveCars(numberGenerator);
            lap.reduce();
        }
        return GameResponseDto.createByDomain(getWinners(cars), cars);
    }

    private static String getWinners(final Cars cars) {
        final WinnerMaker winnerMaker = new WinnerMaker();
        final List<String> winners = winnerMaker.getWinnerCarsName(cars.getLatestResult());
        return ListJoiner.join(winners);
    }
}
