package racingcar.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.utils.powerGenerator.PowerGenerator;
import racingcar.view.ResultView;

public class RacingGame {
    private final List<Car> cars;
    private final PowerGenerator powerGenerator;
    private int tryCount;

    public RacingGame(final String[] carNames, final int tryCount, final PowerGenerator powerGenerator) {
        this.powerGenerator = powerGenerator;
        this.cars = generateCars(carNames);
        this.tryCount = tryCount;
    }

    @Override
    public String toString() {
        return "RacingGame{" +
                "cars=" + cars +
                ", tryCount=" + tryCount +
                '}';
    }

    public void start() {
        ResultView.printStart();
        while (!isEnd()) {
            moveCars();
            ResultView.printPositionOfCars(cars);
        }
    }

    public List<Car> getWinners() {
        final int maxPosition = getMaxPosition();
        return cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .collect(Collectors.toList());
    }

    private List<Car> generateCars(final String[] carNames) {
        final List<Car> cars = new ArrayList<>();
        for (final String carName : carNames) {
            cars.add(new Car(carName));
        }
        return cars;
    }

    private boolean isEnd() {
        if (tryCount > 0) {
            tryCount--;
            return false;
        }
        return true;
    }

    private void moveCars() {
        for (final Car car : cars) {
            final int power = powerGenerator.generate();
            car.move(power);
        }
    }

    private int getMaxPosition() {
        return cars.stream()
                .mapToInt(Car::getPosition)
                .max().orElse(0);
    }

    public RacingGameResultDto convert() {
        final List<RacingCarDto> racingCarDtos = cars.stream()
                .map(Car::convert)
                .collect(Collectors.toList());

        final StringBuilder stringBuilder = new StringBuilder();
        for (final Car car : getWinners()) {
            stringBuilder.append(car.getName());
        }

        return new RacingGameResultDto(stringBuilder.toString(), racingCarDtos);
    }
}
