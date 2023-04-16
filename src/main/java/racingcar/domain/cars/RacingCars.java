package racingcar.domain.cars;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RacingCars {
    private final List<RacingCar> racingCars;

    public RacingCars(List<RacingCar> racingCars) {
        validate(racingCars);
        this.racingCars = racingCars;
    }

    private void validate(List<RacingCar> racingCars) {
        if (hasSameName(racingCars)) {
            throw new IllegalArgumentException("자동차의 이름은 중복일 수 없습니다.");
        }
    }

    private boolean hasSameName(List<RacingCar> racingCars) {
        long distinctNameCount = racingCars.stream()
                .map(RacingCar::getName)
                .distinct()
                .count();
        return racingCars.size() != distinctNameCount;
    }

    public void moveCars(List<Integer> numbers) {
        for (int index = 0; index < racingCars.size(); index++) {
            racingCars.get(index).moveDependingOn(numbers.get(index));
        }
    }

    public int calculateMaxPosition() {
        return racingCars.stream().mapToInt(RacingCar::getPosition).max().orElse(0);
    }

    public List<RacingCar> filter(Predicate<RacingCar> predicate) {
        return racingCars.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public List<RacingCar> getCars() {
        return Collections.unmodifiableList(racingCars);
    }
}
