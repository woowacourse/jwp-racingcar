package racingcar.domain;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class RacingResult {
    private final LinkedHashMap<Name, Position> history;

    public RacingResult(final List<Car> cars) {
        validate(cars);
        this.history = createHistory(cars);
    }

    private LinkedHashMap<Name, Position> createHistory(final List<Car> cars) {
        return cars.stream()
                .collect(Collectors.toMap(
                        Car::getName,
                        Car::getPosition,
                        (key1, key2) -> key1,
                        LinkedHashMap::new));
    }

    private void validate(final List<Car> cars) {
        if (cars.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 레이싱 게임 결과는 비어있을 수 없습니다.");
        }
    }

    public List<Name> pickWinner() {
        final int maxValue = findMaxPosition();

        return history.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getValue() == maxValue)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private int findMaxPosition() {
        return history.values()
                .stream()
                .map(Position::getValue)
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 레이싱 게임 결과 히스토리가 존재하지 않습니다."));
    }

    public LinkedHashMap<Name, Position> getHistory() {
        return new LinkedHashMap<>(history);
    }
}
