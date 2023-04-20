package racingcar.entity;

import racingcar.dto.PlayResultDto;

import java.sql.Timestamp;

public class PlayResultEntity {
    private final Integer id;
    private final String winners;
    private final int playCount;
    private final Timestamp createdAt;

    public PlayResultEntity(final Integer id, final String winners, final int playCount, final Timestamp createdAt) {
        this.id = id;
        this.winners = winners;
        this.playCount = playCount;
        this.createdAt = createdAt;
    }

    public static PlayResultEntity from(PlayResultDto playResultDto) {
        return new PlayResultEntity(null, playResultDto.getWinners(), playResultDto.getPlayCount(), null);
    }

    public int getId() {
        return id;
    }

    public String getWinners() {
        return winners;
    }

    public int getPlayCount() {
        return playCount;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
