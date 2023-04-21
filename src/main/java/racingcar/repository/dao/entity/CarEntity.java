package racingcar.repository.dao.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class CarEntity {

    private final Long playRecordId;
    private final String name;
    private final Integer position;
}
