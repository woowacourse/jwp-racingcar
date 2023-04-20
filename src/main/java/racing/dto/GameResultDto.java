package racing.dto;

import java.util.List;
import java.util.stream.Collectors;
import racing.domain.Game;

public class GameResultDto {

    private final List<CarDto> racingCars;
    private final String winners;

    public GameResultDto(final Game game) {
        this.racingCars = initCarDtosUsingGame(game);
        this.winners = getWinners(this.racingCars).stream()
            .map(CarDto::getName)
            .collect(Collectors.joining(","));
    }

    public GameResultDto(final List<CarDto> racingCars) {
        this.racingCars = racingCars;
        this.winners = getWinners(this.racingCars).stream()
            .map(CarDto::getName)
            .collect(Collectors.joining(","));
    }

    private List<CarDto> initCarDtosUsingGame(final Game game) {
        return game.getCars().stream()
            .map(CarDto::new)
            .collect(Collectors.toUnmodifiableList());
    }

    private List<CarDto> getWinners(final List<CarDto> carDtos) {
        return carDtos.stream()
            .filter(CarDto::isWinner)
            .collect(Collectors.toUnmodifiableList());
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }

    public String getWinners() {
        return winners;
    }
}
