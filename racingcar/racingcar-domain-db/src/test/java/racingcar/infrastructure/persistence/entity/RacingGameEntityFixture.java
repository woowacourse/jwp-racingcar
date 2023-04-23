package racingcar.infrastructure.persistence.entity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class RacingGameEntityFixture {

    public static List<RacingGameEntity> racingGameEntitiesOfSize(final int size) {
        return LongStream.rangeClosed(1, size)
                .mapToObj(it -> new RacingGameEntity(it, size))
                .collect(Collectors.toList());
    }
}
