package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.game.Game;

public class RaceResultDto {

    private final List<CarDto> carDtos;
    private final List<CarDto> winners;

    public RaceResultDto(Game game) {
        this.carDtos = carsInit(game);
        this.winners = winnersInit(game);
    }

    private List<CarDto> carsInit(Game game) {
        return game.getCars().stream()
            .map(CarDto::new)
            .collect(Collectors.toUnmodifiableList());
    }

    private List<CarDto> winnersInit(Game game) {
        return game.getWinners().stream()
            .map(CarDto::new)
            .collect(Collectors.toUnmodifiableList());
    }

    public List<CarDto> getCarDtos() {
        return carDtos;
    }

    public List<CarDto> getWinners() {
        return winners;
    }
}
