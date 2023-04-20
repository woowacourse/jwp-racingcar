package racingcar.entity;

import java.time.LocalDateTime;

public class Game {

    private Integer id;
    private int trialCount;
    private LocalDateTime createdAt;

    public Game(Integer id, int trialCount, LocalDateTime createdAt) {
        this.id = id;
        this.trialCount = trialCount;
        this.createdAt = createdAt;
    }

    public static Game from(int trialCount) {
        return new Game(null, trialCount, null);
    }

    public Integer getId() {
        return id;
    }
    
    public int getTrialCount() {
        return trialCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
