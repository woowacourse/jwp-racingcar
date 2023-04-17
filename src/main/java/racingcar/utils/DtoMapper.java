package racingcar.utils;

import racingcar.domain.Cars;
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
}
