package racingcar.domain.game;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.cars.RacingCar;
import racingcar.domain.cars.RacingCars;

public class RacingGame {

    private final long id;
    private final RacingCars racingCars;


    public RacingGame(long id, List<RacingCar> racingCars) {
        this.id = id;
        this.racingCars = new RacingCars(racingCars);
    }

    public static RacingGame create(long gameHistoryId, List<String> carNames) {
        List<RacingCar> racingCars = createRacingCars(gameHistoryId, carNames);
        return new RacingGame(gameHistoryId, racingCars);
    }

    private static List<RacingCar> createRacingCars(long gameId, List<String> carNames) {
        List<RacingCar> racingCars = carNames.stream()
                .map(carName -> new RacingCar(gameId, carName))
                .collect(Collectors.toList());
        return racingCars;
    }

    public void play(int trialCount, NumberGenerator numberGenerator) {
        for (int count = 0; count < trialCount; count++) {
            List<Integer> numbers = numberGenerator.generateNumbers(getRacingCars().size());
            racingCars.moveCars(numbers);
        }
    }

    public boolean isWinner(RacingCar racingCar) {
        return racingCars.isWinner(racingCar);
    }

    public List<RacingCar> getRacingCars() {
        return racingCars.getCars();
    }

}
