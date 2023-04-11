package racingcar.domain;

import racingcar.dto.GameInputDto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static racingcar.option.Option.MIN_TRIAL_COUNT;

public class SpringService {

    private final List<Car> cars;
    private final MoveChance moveChance;
    private final int count;

    public SpringService(GameInputDto gameInputDto,int count, MoveChance moveChance) {
        this.cars = List.of(gameInputDto.getNames().split(","))
                .stream()
                .map(name -> new Car(name))
                .collect(Collectors.toList());
        this.moveChance = moveChance;
        validateNotNegativeInteger(count);
        this.count = count;
    }

    public List<Car> findWinners() {
        int maxPosition = findMaxPosition();
        return cars.stream()
                .filter(car -> car.hasSamePositionWith(maxPosition))
                .collect(Collectors.toList());
    }

    private int findMaxPosition() {
        int maxPosition = 0;
        for (Car car : cars) {
            maxPosition = car.selectMaxPosition(maxPosition);
        }
        return maxPosition;
    }

    public void playOnce() {
        for (Car car : cars) {
            car.move(moveChance);
        }
    }

    public List<Car> getCars(){
        return Collections.unmodifiableList(cars);
    }

    public void play() {
        playMultipleTimes();
    }

    private void validateNotNegativeInteger(int trialCount) {
        if (trialCount < MIN_TRIAL_COUNT) {
            throw new IllegalArgumentException("[ERROR] 시도횟수는 음수이면 안됩니다.");
        }
    }

    private void playMultipleTimes() {
        for (int i = 0; i < count; i++) {
            playOnce();
        }
    }
}
