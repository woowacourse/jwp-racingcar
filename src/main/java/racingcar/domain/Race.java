package racingcar.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Race {

    public static final int MIN_TRIAL_COUNT = 1;
    public static final int MAX_TRIAL_COUNT = 10;
    public static final String TRIAL_COUNT_ERROR_MESSAGE =
            "올바르지 않은 시도횟수입니다.(" + MIN_TRIAL_COUNT + " ~ " + MAX_TRIAL_COUNT + ")";

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

    private void validateRange(final int count) {
        if (count < MIN_TRIAL_COUNT || MAX_TRIAL_COUNT < count) {
            throw new IllegalArgumentException(TRIAL_COUNT_ERROR_MESSAGE);
        }
    }

    public void play() {
        while (!isFinished()) {
            participants.drive(numberGenerator);
            addCount();
        }
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

    private boolean isFinished() {
        return totalCount == currentCount;
    }
}
