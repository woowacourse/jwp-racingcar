package racingcar.domain;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.IntStream;

public class RacingGame {


    private static final Integer DEFAULT_GAME_ID = null;
    private static final int RACE_START_POINT = 0;
    private static final int MINIMUM_RANDOM_NUMBER = 0;
    private static final int MAXIMUM_RANDOM_NUMBER = 9;

    private Integer gameId;
    private final LocalTime created_at;
    private final RacingCars racingCars;
    private final TryCount tryCount;

    public RacingGame(final Integer gameId, final LocalTime created_at, final RacingCars racingCars, final TryCount tryCount) {
        this.gameId = gameId;
        this.created_at = created_at;
        this.racingCars = racingCars;
        this.tryCount = tryCount;
    }

    //todo: racingGame 객체로 RacingCars과 TryCount를 필드로 묶고, 아래와 같은 정적 팩토리 메서드에 대한 의견 궁금합니다.
    public static RacingGame of(List<String> carNames, int tries) {
        List<Car> cars = CarFactory.generate(carNames, RACE_START_POINT);
        RacingCars racingCars = new RacingCars(cars);
        TryCount tryCount = new TryCount(tries);

        return new RacingGame(DEFAULT_GAME_ID, LocalTime.now(), racingCars, tryCount);
    }

    public void race(NumberGenerator numberGenerator) {
        IntStream.range(0, tryCount.getTries())
                .forEach(ignored -> racingCars.moveCars(numberGenerator));
    }

    public List<String> pickWinnerCarNames() {
        return racingCars.pickWinnerCarNames();
    }

    public int getGameId() {
        return gameId;
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
