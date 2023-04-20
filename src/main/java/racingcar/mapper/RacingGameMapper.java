package racingcar.mapper;

import racingcar.dto.RacingGameDto;
import racingcar.dto.view.PlaySuccessResponse;
import racingcar.model.RacingGame;
import racingcar.model.car.Car;

import java.util.List;
import java.util.stream.Collectors;

public class RacingGameMapper {

    public static RacingGameDto toRacingGameDto(RacingGame racingGame) {
        List<String> winners = racingGame.getWinnerNames();
        List<Car> cars = racingGame.getCars();
        int moveCount = racingGame.getMoveCount();
        return new RacingGameDto(winners, CarMapper.mapCarsToCarDtos(cars), moveCount);
    }

    public static List<PlaySuccessResponse> toResponse(List<RacingGameDto> racingGameDtos) {
        return racingGameDtos.stream()
                .map(racingGameDto -> new PlaySuccessResponse(racingGameDto.getWinnerNames(), racingGameDto.getCars()))
                .collect(Collectors.toList());
    }

    public static PlaySuccessResponse toResponse(RacingGameDto racingGameDto) {
        return new PlaySuccessResponse(racingGameDto.getWinnerNames(), racingGameDto.getCars());
    }
}
