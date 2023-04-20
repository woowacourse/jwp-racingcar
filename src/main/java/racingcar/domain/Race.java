package racingcar.domain;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.utils.NumberGenerator;

public class Race {

    private final static int MIN_COUNT = 1;
    private final static int MAX_COUNT = 10;

    private final int totalCount;
    private final Participants participants;
    private final NumberGenerator numberGenerator;
    private int currentCount = 0;

    public Race(final int totalCount, final List<String> carNames, NumberGenerator numberGenerator) {
        validateRange(totalCount);
        this.totalCount = totalCount;
        this.numberGenerator = numberGenerator;
        List<Car> cars = carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
        this.participants = new Participants(cars);
    }

    public void playRound() {
        participants.drive(numberGenerator);
        addCount();
    }

    public List<Car> getParticipants() {
        return participants.getCars();
    }

    public List<Car> findWinners() {
        return participants.findWinners();
    }

    private void addCount() {
        currentCount += 1;
    }

    public boolean isFinished() {
        return totalCount == currentCount;
    }

    private void validateRange(final int count) {
        final String NOT_PROPER_COUNT = "[ERROR] 올바르지 않은 시도횟수입니다.(1 ~ 999,999,999)";

        if (count < MIN_COUNT || count > MAX_COUNT) {
            throw new IllegalArgumentException(NOT_PROPER_COUNT);
        }
    }
}
