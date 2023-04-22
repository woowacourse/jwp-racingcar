package racingcar.dao.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CarEntity {

    private final Long playRecordId;
    private final String name;
    private final Integer position;
}
