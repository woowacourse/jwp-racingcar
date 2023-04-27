package racingcar.controller.dto;

import java.util.List;

import racingcar.domain.dto.RacingCarResultDto;

public class RacingCarGameResponse {

    private final String winners;
    private final List<CarDto> racingCars;

    private RacingCarGameResponse(final String winners, final List<CarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static RacingCarGameResponse from(final RacingCarResultDto racingCarResultDto) {
        String winners = String.join(",", racingCarResultDto.getWinners());
        List<CarDto> racingCars = CarDto.from(racingCarResultDto.getCars());
        return new RacingCarGameResponse(winners, racingCars);
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
