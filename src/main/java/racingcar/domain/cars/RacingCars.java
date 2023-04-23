package racingcar.domain.cars;

import java.util.Collections;
import java.util.List;
import racingcar.domain.game.NumberGenerator;

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

    public void moveCars(NumberGenerator numberGenerator) {
        for(RacingCar racingCar: racingCars) {
            racingCar.moveDependingOn(numberGenerator.generateNumber());
        }
    }

    public boolean isWinner(RacingCar car){
        if (!racingCars.contains(car)) {
            throw new IllegalArgumentException("포함되어있지 않은 차입니다.");
        }
        return calculateMaxPosition() == car.getPosition();
    }

    private int calculateMaxPosition() {
        return racingCars.stream().mapToInt(RacingCar::getPosition).max().orElse(0);
    }

    public List<RacingCar> getCars() {
        return Collections.unmodifiableList(racingCars);
    }
}
