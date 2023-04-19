package racingcar.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Game {

    private Integer id;
    private String winners;
    private int trialCount;
    private LocalDateTime createdAt;

    private Game(Integer id, String winners, int trialCount, LocalDateTime createdAt) {
        this.id = id;
        this.winners = winners;
        this.trialCount = trialCount;
        this.createdAt = createdAt;
    }

    public static Game of(Integer id, List<String> winners, int trialCount) {
        return new Game(id, String.join(",", winners), trialCount, null);
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
