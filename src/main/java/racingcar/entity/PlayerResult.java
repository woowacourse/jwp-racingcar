package racingcar.entity;

import java.sql.Timestamp;

public class PlayerResult {
    Integer id;
    Integer count;
    String winners;
    Timestamp dateTime;

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

    public void setId(final Integer id) {
        this.id = id;
    }

    public void setCount(final Integer count) {
        this.count = count;
    }

    public void setWinners(final String winners) {
        this.winners = winners;
    }

    public void setDateTime(final Timestamp dateTime) {
        this.dateTime = dateTime;
    }
}
