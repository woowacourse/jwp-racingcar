package racingcar.domain.game;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.cars.RacingCar;
import racingcar.domain.cars.RacingCarDto;

public class RacingGameDto {
    private final List<String> winnerNames;
    private final List<RacingCarDto> racingCars;

    private RacingGameDto(List<RacingCar> racingRacingCars, List<RacingCar> winnerNames) {
        this.winnerNames = convertToString(winnerNames);
        this.racingCars = convertToCarDto(racingRacingCars);
    }

    public static RacingGameDto from(RacingGame game) {
        return new RacingGameDto(game.getRacingCars(), game.calculateWinners());
    }

    private List<String> convertToString(List<RacingCar> winners) {
        return winners.stream()
                .map(RacingCar::getName)
                .collect(Collectors.toList());
    }

    private List<RacingCarDto> convertToCarDto(List<RacingCar> racingRacingCars) {
        return racingRacingCars.stream()
                .map(RacingCarDto::from)
                .collect(Collectors.toList());
    }

    public List<String> getWinnerNames() {
        return winnerNames;
    }

    public List<RacingCarDto> getRacingCars() {
        return racingCars;
    }

}
