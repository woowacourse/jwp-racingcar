package racingcar.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RacingCars {
    private final List<RacingCar> racingCars = new ArrayList<>();

    public RacingCars(final List<RacingCar> racingCars) {
        this.racingCars.addAll(racingCars);
    }

    public void moveAll() {
        for (RacingCar racingCar : this.racingCars) {
            racingCar.move();
        }
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }

    public List<String> getWinnerNames() {
        int maxPosition = getMaxPosition();

        return racingCars.stream()
            .filter(racingCar -> racingCar.getPosition() == maxPosition)
            .map(RacingCar::getName)
            .collect(Collectors.toUnmodifiableList());
    }

    private int getMaxPosition() {
        return this.racingCars
            .stream()
            .mapToInt(RacingCar::getPosition)
            .max()
            .orElse(0);
    }
}
