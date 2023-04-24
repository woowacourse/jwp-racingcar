package racingcar.dao.entity;

import java.time.LocalTime;
import lombok.Getter;

@Getter
public class PlayRecordEntity {

    private final Long id;
    private final Integer count;
    private final LocalTime createdAt;

    public PlayRecordEntity(final Integer count) {
        this.id = null;
        this.count = count;
        this.createdAt = null;
    }
}
