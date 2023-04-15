package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.game.Game;

public class RacingResultRequestDto {

    private final List<CarDto> winners;
    private final List<CarDto> carDtos;

    public RacingResultRequestDto(Game game) {
        this.winners = winnersInit(game);
        this.carDtos = carsInit(game);
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

    public List<CarDto> getWinners() {
        return winners;
    }
    
    public List<CarDto> getCarDtos() {
        return carDtos;
    }
}
