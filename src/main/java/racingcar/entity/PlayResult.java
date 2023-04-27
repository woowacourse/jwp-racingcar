package racingcar.entity;

import java.time.LocalDateTime;

public class PlayResult {
    private final Integer id;
    private final Integer count;
    private final String winners;
    private final LocalDateTime createdAt;

    public PlayResult(Integer id, Integer count, String winners, LocalDateTime createdAt) {
        this.id = id;
        this.count = count;
        this.winners = winners;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCount() {
        return count;
    }

    public String getWinners() {
        return winners;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
