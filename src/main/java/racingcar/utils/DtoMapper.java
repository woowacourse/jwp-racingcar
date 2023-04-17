package racingcar.utils;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.db.CarDto;
import racingcar.dto.db.GameResultDto;
import racingcar.dto.db.GameResultWithCarDto;
import racingcar.dto.response.CarResponseDto;
import racingcar.dto.response.GameResultResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    private static GameResultResponseDto mapToGameResultResponseDto(final List<String> winners, final List<CarResponseDto> carResponseDtos) {
        return new GameResultResponseDto(winners, carResponseDtos);
    }

    public static GameResultDto toRacingGameDto(final RacingGame racingGame) {
        return new GameResultDto(racingGame.getTryCountValue());
    }

    public static CarDto toCarDto(final Car car) {
        return new CarDto(car.getNameValue(), car.getPositionValue());
    }

    public static List<CarDto> toCarsDto(final Long gameId, final RacingGame racingGame) {
        return racingGame.getCars()
                .stream()
                .map(car -> new CarDto(gameId, car.getNameValue(), car.getPositionValue(), car.isWinner()))
                .collect(Collectors.toList());
    }

    public static List<GameResultResponseDto> mapToGameResultResponseDtos(final Map<Long, List<GameResultWithCarDto>> dtosById) {
        List<GameResultResponseDto> result = new ArrayList<>();
        for (Map.Entry<Long, List<GameResultWithCarDto>> entry : dtosById.entrySet()) {
            final List<String> winners = entry.getValue()
                    .stream()
                    .filter(GameResultWithCarDto::isWinner)
                    .map(GameResultWithCarDto::getName)
                    .collect(Collectors.toList());
            final List<CarResponseDto> carResponseDtos = entry.getValue()
                    .stream()
                    .map(m -> new CarResponseDto(m.getName(), m.getPosition()))
                    .collect(Collectors.toList());
            result.add(new GameResultResponseDto(winners, carResponseDtos));
        }
        return result;
    }
}
