package racingcar.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.repository.RacingGameRepository;

public class StubRacingGameRepository implements RacingGameRepository {

    private final Map<Integer, RacingGame> gameIdToRacingGame = new HashMap<>();
    private int maxCarId = 0;
    private int maxGameId = 0;

    @Override
    public RacingGame insert(final RacingGame racingGame) {
        final List<Car> carWithId = racingGame.getCars()
                .stream()
                .map(car -> new Car(car.getCarName(), car.getPosition(), ++maxCarId))
                .collect(Collectors.toList());
        final RacingGame racingGameWithId = new RacingGame(
                ++maxGameId,
                carWithId,
                racingGame.getCount().getTargetCount());
        gameIdToRacingGame.put(maxGameId, racingGameWithId);
        return racingGameWithId;
    }

    public void setGameIdToRacingGame(final Map<Integer, RacingGame> gameIdToRacingGame) {
        this.gameIdToRacingGame.clear();
        this.gameIdToRacingGame.putAll(gameIdToRacingGame);
    }

    @Override
    public List<RacingGame> findAll() {
        return new ArrayList<>(gameIdToRacingGame.values());
    }
}
