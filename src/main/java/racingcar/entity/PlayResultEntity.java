package racingcar.entity;

import java.sql.Timestamp;

public class PlayResultEntity {
    private final Integer id;
    private final Integer count;
    private final String winners;
    private final Timestamp dateTime;

    public PlayResultEntity(final Integer id, final Integer count, final String winners, final Timestamp dateTime) {
        this.id = id;
        this.count = count;
        this.winners = winners;
        this.dateTime = dateTime;
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

    public Timestamp getDateTime() {
        return dateTime;
    }
}
