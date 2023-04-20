package racingcar.persistence.repository;

import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.persistence.entity.GameResultEntity;
import racingcar.persistence.entity.PlayerResultEntity;

import java.util.List;
import java.util.stream.Collectors;

public class RacingGameConverter {

    public List<RacingGame> convertAll(
            final List<GameResultEntity> gameResults,
            final List<PlayerResultEntity> playerResults
    ) {
        return gameResults.stream()
                .map(gameResult -> new RacingGame(
                        collectCarNames(playerResults, gameResult.getId()),
                        gameResult.getTrialCount(),
                        new RandomNumberGenerator()
                )).collect(Collectors.toList());
    }

    private List<String> collectCarNames(
            final List<PlayerResultEntity> playerResults,
            final int gameResultId
    ) {
        return playerResults.stream()
                .filter(playerResultEntity -> playerResultEntity.getGameResultId() == gameResultId)
                .map(playerResult -> new Car(playerResult.getName(), playerResult.getPosition()))
                .map(Car::getCarName)
                .collect(Collectors.toList());
    }
}
