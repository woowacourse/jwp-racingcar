package racingcar.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.cars.RacingCar;
import racingcar.domain.game.RacingGame;

public class ConsoleRacingGameRepository implements RacingGameRepository {

    private final List<RacingGame> gameHistory = new ArrayList<>();
    private final List<RacingCar> racingCars = new ArrayList<>();

    @Override
    public RacingGame create(RacingGame racingGame) {
        List<RacingCar> createdCars = racingGame.getRacingCars().stream()
                .map(racingCar -> new RacingCar(racingCars.size() + 1L, racingCar.getName(), racingCar.getPosition()))
                .collect(Collectors.toList());
        RacingGame createdGame = new RacingGame(gameHistory.size() + 1L, racingGame.getTrialCount(),
                createdCars, racingGame.getPlayTime());
        gameHistory.add(createdGame);
        racingCars.addAll(createdCars);
        return createdGame;
    }

    @Override
    public List<RacingGame> findAll() {
        return gameHistory;
    }
}
