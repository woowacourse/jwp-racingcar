package racingcar.dto.request;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;

public class RacingGameRequest {

    private static final String NAME_DELIMITER = ",";

    @NotBlank(message = "이름을 입력해주세요.")
    private final String names;

    @NotNull(message = "횟수를 입력해주세요.")
    private final Integer count;

    public RacingGameRequest(String names, Integer count) {
        this.names = names;
        this.count = count;
    }

    public RacingGame toEntity() {
        Cars cars = Arrays.stream(names.split(NAME_DELIMITER))
                .map(Car::new)
                .collect(collectingAndThen(toList(), Cars::new));

        return new RacingGame(cars, count);
    }

    public String getNames() {
        return names;
    }

    public Integer getCount() {
        return count;
    }
}
