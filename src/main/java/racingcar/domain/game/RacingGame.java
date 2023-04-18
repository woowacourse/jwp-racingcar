package racingcar.domain.game;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import racingcar.domain.cars.RacingCar;
import racingcar.domain.cars.RacingCars;

public class RacingGame {

    private Long id;
    private final RacingCars racingCars;


    public RacingGame(List<RacingCar> racingCars) {
        this.racingCars = new RacingCars(racingCars);
    }

    public RacingGame(Long id, List<RacingCar> racingCars) {
        this.id = id;
        this.racingCars = new RacingCars(racingCars);
    }

    public static RacingGame from(List<String> carNames) {
        List<RacingCar> racingCars = createRacingCars(carNames);
        return new RacingGame(racingCars);
    }

    private static List<RacingCar> createRacingCars(List<String> carNames) {
        List<RacingCar> racingCars = carNames.stream()
                .map(carName -> new RacingCar(carName))
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

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        RacingGame that = (RacingGame) o;
        if (id == null || that.id == null) {
            return this == o;
        }
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
