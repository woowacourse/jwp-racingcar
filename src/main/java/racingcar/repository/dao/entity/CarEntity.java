package racingcar.repository.dao.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class CarEntity {

    private final Long playRecordId;
    private final String name;
    private final int position;

    public CarEntity(final Long playRecordId, final String name, final int position) {
        this.playRecordId = playRecordId;
        this.name = name;
        this.position = position;
    }

    public CarEntity(final String name, final int position) {
        this(null, name, position);
    }
}
