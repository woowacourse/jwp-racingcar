package racingcar.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class RacingHistories {
    private final List<History> histories;

    public RacingHistories(final List<Car> cars) {
        validate(cars);
        this.histories = parseHistoryBy(cars);
    }

    private void validate(final List<Car> cars) {
        if (cars.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 레이싱 게임 결과는 비어있을 수 없습니다.");
        }
    }

    private List<History> parseHistoryBy(final List<Car> cars) {
        return cars.stream()
                .map(car -> new History(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
    }

    public List<Name> pickWinner() {
        final int maxValue = findMaxPosition();

        return histories.stream()
                .filter(history -> history.getPositionValue() == maxValue)
                .map(History::getName)
                .collect(Collectors.toList());
    }

    private int findMaxPosition() {
        return histories.stream()
                .map(History::getPositionValue)
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 레이싱 게임 결과 히스토리가 존재하지 않습니다."));
    }

    public List<History> getHistories() {
        return new ArrayList<>(histories);
    }
}
