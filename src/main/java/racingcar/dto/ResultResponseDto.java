package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;
import racingcar.domain.RacingCarGame;

public class ResultResponseDto {

    private final String winners;
    private final List<PlayerDto> racingCars;

    private ResultResponseDto(String winners, List<PlayerDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static ResultResponseDto of(RacingCarGame racingCarGame) {
        String winners = racingCarGame.findWinners().getAll().stream()
            .map(Car::getName)
            .collect(Collectors.joining(","));

        List<PlayerDto> playerDtos = racingCarGame.getCars().getAll().stream()
            .map(car -> new PlayerDto(car.getName(), car.getPosition()))
            .collect(Collectors.toList());

        return new ResultResponseDto(winners, playerDtos);
    }

    public String getWinners() {
        return winners;
    }

    public List<PlayerDto> getRacingCars() {
        return racingCars;
    }
}
