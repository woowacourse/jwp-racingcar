package racingcar.repository;

import java.util.ArrayList;
import java.util.List;
import racingcar.domain.cars.RacingCar;
import racingcar.domain.game.RacingGame;

public class ConsoleRacingGameRepository implements RacingGameRepository {

    private final List<RacingGame> gameHistory = new ArrayList<>();
    private final List<RacingCar> racingCars = new ArrayList<>();

    @Override
    public RacingGame save(RacingGame racingGame) {
        List<RacingCar> savedCars = new ArrayList<>();
        for (RacingCar racingCar : racingGame.getRacingCars()) {
            RacingCar savedCar = new RacingCar(racingCars.size() + 1L, racingCar.getName(), racingCar.getPosition());
            savedCars.add(savedCar);
        }
        RacingGame savedRacingGame = new RacingGame(gameHistory.size() + 1L, racingGame.getTrialCount(),
                savedCars, racingGame.getPlayTime());
        gameHistory.add(savedRacingGame);
        return savedRacingGame;
    }

    @Override
    public List<RacingGame> findAll() {
        return gameHistory;
    }
}
