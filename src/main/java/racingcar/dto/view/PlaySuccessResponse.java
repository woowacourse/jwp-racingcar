package racingcar.dto.view;

import racingcar.dto.CarDto;
import racingcar.dto.RacingGameResultDto;

import java.util.List;
import java.util.stream.Collectors;

public class PlaySuccessResponse {

    private final List<String> winners;
    private final List<CarDto> racingCars;

    public PlaySuccessResponse(List<String> winners, List<CarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static PlaySuccessResponse from(RacingGameResultDto racingGameDto) {
        return new PlaySuccessResponse(racingGameDto.getWinnerNames(), racingGameDto.getCars());
    }

    public static List<PlaySuccessResponse> from(List<RacingGameResultDto> racingGameDtos) {
        return racingGameDtos.stream()
                .map(racingGameDto -> new PlaySuccessResponse(racingGameDto.getWinnerNames(), racingGameDto.getCars()))
                .collect(Collectors.toList());
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
