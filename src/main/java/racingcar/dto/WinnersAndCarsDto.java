package racingcar.dto;

import java.util.List;

import racingcar.service.RacingCarResult;

public class WinnersAndCarsDto {

    private final String winners;
    private final List<CarDto> racingCars;

    private WinnersAndCarsDto(final String winners, final List<CarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static WinnersAndCarsDto from(final RacingCarResult racingCarResult) {
        String winners = String.join(",", racingCarResult.getWinners());
        List<CarDto> racingCars = CarDto.from(racingCarResult.getCars());
        return new WinnersAndCarsDto(winners, racingCars);
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
