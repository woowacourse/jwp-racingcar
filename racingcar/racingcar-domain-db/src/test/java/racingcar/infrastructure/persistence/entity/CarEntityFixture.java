package racingcar.infrastructure.persistence.entity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CarEntityFixture {

    public static List<CarEntity> carEntitiesOfSize(final int size, final Long gameId) {
        return IntStream.range(0, size)
                .mapToObj(it -> new CarEntity("name" + it, it, gameId))
                .collect(Collectors.toList());
    }
}
