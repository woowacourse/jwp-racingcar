package racingcar.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;

public class Game {

    private Integer id;
    private String winners;
    private int trialCount;
    private LocalDateTime createdAt;

    public Game(Integer id, String winners, int trialCount, LocalDateTime createdAt) {
        this.id = id;
        this.winners = winners;
        this.trialCount = trialCount;
        this.createdAt = createdAt;
    }

    public static Game of(List<Car> winners, int trialCount) {
        List<String> winnerNames = winners.stream()
                .map(Car::getName)
                .collect(Collectors.toList());
        return new Game(null, String.join(",", winnerNames), trialCount, null);
    }

    public Integer getId() {
        return id;
    }

    public String getWinners() {
        return winners;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
