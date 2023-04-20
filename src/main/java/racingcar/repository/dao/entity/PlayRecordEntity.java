package racingcar.repository.dao.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlayRecordEntity {

    private final Long id;
    private final Integer count;
}
