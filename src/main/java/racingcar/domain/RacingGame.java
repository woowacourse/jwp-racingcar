package racingcar.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import racingcar.dto.GameResultDto;
import racingcar.dto.PlayerDto;
import racingcar.utils.PowerGenerator;
import racingcar.utils.RandomPowerGenerator;

public class RacingGame {
    private static final int MIN_TRY_COUNT = 1;
    private static final int MAX_TRY_COUNT = 100;
    private static final int DEFAULT_POSITION = 0;

    private final List<Car> cars;
    private final PowerGenerator powerGenerator;
    private final int playCount;

    public RacingGame(final List<String> names, final int tryCount) {
        validateNames(names);
        validateTryCount(tryCount);
        this.cars = generateCars(names);
        this.playCount = tryCount;
        this.powerGenerator = new RandomPowerGenerator();
    }

    private static void validateTryCount(final int tryCount) {
        if (tryCount < MIN_TRY_COUNT || tryCount > MAX_TRY_COUNT) {
            throw new IllegalArgumentException("1이상 100이하의 시도 횟수를 입력해 주세요.");
        }
    }

    private static void validateNames(final List<String> names) {
        if (names.isEmpty() || names.stream().anyMatch(String::isBlank)) {
            throw new IllegalArgumentException("빈 이름은 입력할 수 없습니다.");
        }
        final Set<String> set = new HashSet<>(names);
        if (set.size() != names.size()) {
            throw new IllegalArgumentException("중복된 이름은 사용할 수 없습니다.");
        }
    }

    public GameResultDto play() {
        for (int i = 0; i < playCount; i++) {
            moveCars();
        }
        updateWinner();
        return getResult();
    }

    public GameResultDto getResult() {
        final List<PlayerDto> players = cars.stream()
                .map(Car::getPlayer)
                .collect(Collectors.toList());
        return new GameResultDto(playCount, players);
    }

    private void updateWinner() {
        final int maxPosition = findMaxPosition();
        final Predicate<Car> maxPredicate = car -> car.getPosition() == maxPosition;
        cars.stream()
                .filter(maxPredicate)
                .forEach(Car::win);
    }

    private List<Car> generateCars(final List<String> names) {
        return names.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    private void moveCars() {
        cars.forEach(car -> car.move(powerGenerator.generate()));
    }

    private int findMaxPosition() {
        return cars.stream()
                .mapToInt(Car::getPosition)
                .max().orElse(DEFAULT_POSITION);
    }
}
