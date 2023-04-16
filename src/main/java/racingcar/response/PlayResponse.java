package racingcar.response;

import lombok.Getter;
import lombok.ToString;
import racingcar.domain.Cars;
import racingcar.dto.CarDto;
import racingcar.dto.RecordDto;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class PlayResponse {

    private final String winners;
    private final List<CarDto> racingCars;

    private PlayResponse(final List<String> winnerNames, final List<CarDto> racingCars) {
        this.winners = String.join(",", winnerNames);
        this.racingCars = racingCars;
    }

    public static PlayResponse from(final Cars cars) {
        return new PlayResponse(cars.winnerNames(), CarDto.toListFrom(cars));
    }

    public static PlayResponse from(final List<RecordDto> recordDtos) {
        List<String> winners = new ArrayList<>();
        List<CarDto> carDtos = new ArrayList<>();

        for (final RecordDto recordDto : recordDtos) {
            decideWinner(winners, recordDto);
            carDtos.add(CarDto.from(recordDto));
        }

        return new PlayResponse(winners, carDtos);
    }

    private static void decideWinner(final List<String> winners, final RecordDto recordDto) {
        if (recordDto.isWinner()) {
            winners.add(recordDto.getPlayerName());
        }
    }
}
