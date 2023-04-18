package racingcar.dto;

import racingcar.model.RacingGame;
import racingcar.model.car.Car;

import java.util.List;
import java.util.stream.Collectors;

public class RacingGameMapper {

    public static RacingGameDto toGameResultDto(RacingGame racingGame){
        List<String> winners = racingGame.getWinnerNames();
        List<Car> cars = racingGame.getCars();
        int moveCount = racingGame.getMoveCount();
        return new RacingGameDto(winners, CarMapper.toCarDtos(cars), moveCount);
    }

    public static ResponseDto toResponseDto(RacingGame racingGame){
        List<CarDto> carDtos = CarMapper.toCarDtos(racingGame.getCars());
        return new ResponseDto(racingGame.getWinnerNames(), carDtos);
    }

    public static List<ResponseDto> toResponseDto(List<RacingGameDto> racingGameDtos){
        return racingGameDtos.stream()
                .map(racingGameDto -> new ResponseDto(racingGameDto.getWinnerNames(), racingGameDto.getCars()))
                .collect(Collectors.toList());
    }
}
