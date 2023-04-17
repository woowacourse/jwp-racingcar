package racingcar.utils;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;
import racingcar.dto.GameResultResponseDto;

public final class DtoMapper {

    private DtoMapper() {

    }

    /*public static List<Car> mapToCarEntity(final Long gameId, final List<Car> cars) {
        return cars.stream()
                .map(car -> new CarEntity(gameId, car.getNameValue(), car.getPositionValue(), car.isWinner()))
                .collect(Collectors.toList());
    }*/

    public static GameResultResponseDto mapToGameResultResponseDto(final Cars finalResult) {
        /*final List<Car> collect = finalResult.getCars()
                .stream()
                .map(m -> new Car(m.getId(), m.getNameValue(), m.getPositionValue(), m.isWinner()))
                .collect(Collectors.toList());
        */
        return new GameResultResponseDto(finalResult.getCars());
    }

    public static CarDto toCarDto(final Car car) {
        return new CarDto(car.getNameValue(), car.getPositionValue());
    }

    public static GameResultDto toRacingGameDto(final RacingGame racingGame) {
        return new GameResultDto(racingGame.getTryCountValue());
    }
}
