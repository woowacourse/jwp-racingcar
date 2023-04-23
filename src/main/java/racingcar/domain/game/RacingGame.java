package racingcar.domain.game;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import racingcar.domain.cars.RacingCar;
import racingcar.domain.cars.RacingCars;

public class RacingGame {

    private Long id;
    private final RacingCars racingCars;
    private final LocalDateTime playTime;
    private final int trialCount;


    public RacingGame(int trialCount, List<RacingCar> racingCars) {
        validate(trialCount);
        this.trialCount = trialCount;
        this.racingCars = new RacingCars(racingCars);
        this.playTime = LocalDateTime.now();
    }

    public RacingGame(Long id, int trialCount, List<RacingCar> racingCars) {
        validate(trialCount);
        this.id = id;
        this.trialCount = trialCount;
        this.racingCars = new RacingCars(racingCars);
        this.playTime = LocalDateTime.now();
    }

    public RacingGame(Long id, int trialCount, List<RacingCar> racingCars, LocalDateTime playTime) {
        validate(trialCount);
        this.id = id;
        this.trialCount = trialCount;
        this.racingCars = new RacingCars(racingCars);
        this.playTime = playTime;
    }

    private void validate(int trialCount) {
        if (trialCount < 0) {
            throw new IllegalArgumentException("시도 횟수는 음수일 수 없습니다.");
        }
    }

    public static RacingGame of(int trialCount, List<String> carNames) {
        List<RacingCar> racingCars = createRacingCars(carNames);
        return new RacingGame(trialCount, racingCars);
    }

    private static List<RacingCar> createRacingCars(List<String> carNames) {
        List<RacingCar> racingCars = carNames.stream()
                .map(carName -> new RacingCar(carName))
                .collect(Collectors.toList());
        return racingCars;
    }

    public void play(NumberGenerator numberGenerator) {
        for (int count = 0; count < trialCount; count++) {
            racingCars.moveCars(numberGenerator);
        }
    }

    public boolean isWinner(RacingCar racingCar) {
        return racingCars.isWinner(racingCar);
    }

    @Override
    public boolean equals(Object o) {
        RacingGame that = (RacingGame) o;
        if (id == null || that.id == null) {
            return this == o;
        }
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public List<RacingCar> getRacingCars() {
        return racingCars.getCars();
    }

    public LocalDateTime getPlayTime() {
        return playTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
