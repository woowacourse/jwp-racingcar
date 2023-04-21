package racingcar.dao.entity;

import lombok.Getter;

@Getter
public class PlayRecordEntity {

    private final Long id;
    private final Integer count;

    public PlayRecordEntity(final Integer count) {
        this.id = null;
        this.count = count;
    }
}
