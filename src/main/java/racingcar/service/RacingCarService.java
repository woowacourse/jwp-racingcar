package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.PlayerDto;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.ResultResponseDto;
import racingcar.repository.RacingGames;
import racingcar.util.NumberGenerator;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingCarService {

    private final NumberGenerator numberGenerator;
    private final RacingGames racingGames;

    public RacingCarService(NumberGenerator numberGenerator, RacingGames racingGames) {
        this.numberGenerator = numberGenerator;
        this.racingGames = racingGames;
    }

    public ResultResponseDto play(RacingGameRequestDto racingGameRequestDto) {
        RacingGame racingGame = initGame(racingGameRequestDto);
        racingGame.moveCars(numberGenerator);
        RacingGame save = racingGames.save(racingGame);

        List<PlayerDto> playerDtos = mapToPlayerDtos(save.getCars());

        return new ResultResponseDto(save.getWinners(), playerDtos);
    }

    private RacingGame initGame(RacingGameRequestDto racingGameRequestDto) {
        Cars cars = Cars.of(racingGameRequestDto.getNames());
        return new RacingGame(cars, racingGameRequestDto.getCount());
    }

    private List<PlayerDto> mapToPlayerDtos(List<Car> cars) {
        return cars.stream()
                .map(car -> new PlayerDto(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
    }
}
