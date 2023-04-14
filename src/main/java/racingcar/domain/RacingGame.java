package racingcar.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import racingcar.dto.GameResultDto;
import racingcar.dto.PlayerDto;
import racingcar.utils.PowerGenerator;
import racingcar.utils.RandomPowerGenerator;

public class RacingGame {
    private final List<Car> cars;
    private final PowerGenerator powerGenerator;
    private final int playCount;

    public RacingGame(final List<String> carNames, final int tryCount) {
        validateNames(carNames);
        validateTryCount(tryCount);
        this.powerGenerator = new RandomPowerGenerator();
        this.cars = generateCars(carNames);
        this.playCount = tryCount;
    }

    private static void validateTryCount(final int tryCount) {
        if (tryCount < 1 || tryCount > 100) {
            throw new IllegalArgumentException("1이상 100이하의 시도 횟수를 입력해 주세요.");
        }
    }

    private static void validateNames(final List<String> names) {
        if (names.size() < 1 || names.stream().anyMatch(String::isBlank)) {
            throw new IllegalArgumentException("빈 이름은 입력할 수 없습니다.");
        }
        final Set<String> set = new HashSet<>(names);
        if (set.size() != names.size()) {
            throw new IllegalArgumentException("중복된 이름은 사용할 수 없습니다.");
        }
    }

    public void play() {
        int tryCount = playCount;
        while (!isEnd(tryCount--)) {
            moveCars();
        }
    }

    public GameResultDto convertToDto() {
        final List<String> winnerNames = extractWinners().stream()
                .map(Car::getName)
                .collect(Collectors.toList());
        final List<PlayerDto> players = cars.stream()
                .map(Car::convertToDto)
                .collect(Collectors.toList());
        return new GameResultDto(winnerNames, playCount, players);
    }

    private List<Car> extractWinners() {
        final int maxPosition = getMaxPosition();
        return cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .collect(Collectors.toList());
    }

    private List<Car> generateCars(final List<String> carNames) {
        final List<Car> cars = new ArrayList<>();
        for (final String carName : carNames) {
            cars.add(new Car(carName));
        }
        return cars;
    }

    private boolean isEnd(final int tryCount) {
        return tryCount <= 0;
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
}
