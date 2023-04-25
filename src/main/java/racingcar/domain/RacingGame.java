package racingcar.domain;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.IntStream;

public class RacingGame {

    private static final int RACE_START_POINT = 0;

    private final LocalTime created_at;
    private final RacingCars racingCars;
    private final TryCount tryCount;

    private RacingGame(final LocalTime created_at, final RacingCars racingCars, final TryCount tryCount) {
        this.created_at = created_at;
        this.racingCars = racingCars;
        this.tryCount = tryCount;
    }

    //todo: racingGame 객체로 RacingCars과 TryCount를 필드로 묶고, 아래와 같은 정적 팩토리 메서드에 대한 의견 궁금합니다.
    //carFactory를 여기 넣는 게 맞나?
    public static RacingGame of(List<String> carNames, int tries) {
        List<Car> cars = CarFactory.generate(carNames, RACE_START_POINT);
        RacingCars racingCars = new RacingCars(cars);
        TryCount tryCount = new TryCount(tries);

        return new RacingGame(LocalTime.now(), racingCars, tryCount);
    }

    public void race(NumberGenerator numberGenerator) {
        IntStream.range(0, tryCount.getTries())
                .forEach(ignored -> racingCars.moveCars(numberGenerator));
    }

    public List<Car> pickWinningCars() {
        return racingCars.pickWinningCars();
    }
    public List<String> pickWinnerCarNames() {
        return racingCars.pickWinnerCarNames();
    }

    public LocalTime getCreated_at() {
        return created_at;
    }

    public List<Car> getCars() {
        return racingCars.getCars();
    }

    public int getTryCount() {
        return tryCount.getTries();
    }
}
