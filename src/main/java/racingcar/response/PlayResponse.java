package racingcar.response;

import lombok.Getter;
import lombok.ToString;
import racingcar.dto.CarDto;
import java.util.List;

@Getter
@ToString
public class PlayResponse {

    private final String winners;
    private final List<CarDto> racingCars;

    public PlayResponse(final List<String> winners, final List<CarDto> racingCars) {
        this.winners = convertToString(winners);
        this.racingCars = racingCars;
    }

    private String convertToString(final List<String> winners) {
        return String.join(",", winners);
    }
}
