package racingcar.utils;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.db.CarDto;
import racingcar.dto.db.GameResultDto;
import racingcar.dto.response.CarResponseDto;
import racingcar.dto.response.GameResultResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public final class DtoMapper {

    private DtoMapper() {

    }

    public static GameResultResponseDto mapToGameResultResponseDto(final Cars finalResult) {
        final List<String> winners = finalResult.getCars()
                .stream()
                .filter(Car::isWinner)
                .map(Car::getNameValue)
                .collect(Collectors.toList());
        final List<CarResponseDto> carResponseDtos = finalResult.getCars()
                .stream()
                .map(car -> new CarResponseDto(car.getNameValue(), car.getPositionValue()))
                .collect(Collectors.toList());

        return new GameResultResponseDto(winners, carResponseDtos);
    }

    public static CarDto toCarDto(final Car car) {
        return new CarDto(car.getNameValue(), car.getPositionValue());
    }

    public static GameResultDto toRacingGameDto(final RacingGame racingGame) {
        return new GameResultDto(racingGame.getTryCountValue());
    }

    public static List<CarDto> toCarsDto(final Long gameId, final RacingGame racingGame) {
        return racingGame.getCars()
                .stream()
                .map(car -> new CarDto(gameId, car.getNameValue(), car.getPositionValue(), car.isWinner()))
                .collect(Collectors.toList());
    }
}
