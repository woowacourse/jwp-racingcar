package racingcar.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.Count;
import racingcar.domain.RacingCarGame;
import racingcar.dto.PlayerDto;
import racingcar.dto.RacingGameDto;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.ResultResponseDto;
import racingcar.repository.RacingCarRepository;
import racingcar.util.NumberGenerator;

@Service
public class RacingCarService {

    private final NumberGenerator numberGenerator;
    private final RacingCarRepository racingCarRepository;

    public RacingCarService(NumberGenerator numberGenerator, RacingCarRepository racingCarRepository) {
        this.numberGenerator = numberGenerator;
        this.racingCarRepository = racingCarRepository;
    }

    public ResultResponseDto play(RacingGameRequestDto racingGameRequestDto) {
        Cars cars = Cars.of(spiteNameWithComma(racingGameRequestDto));
        Count count = Count.of(racingGameRequestDto.getCount());
        RacingCarGame racingCarGame = new RacingCarGame(cars, count);

        racingCarGame.play(numberGenerator);
        racingCarRepository.save(
            new RacingGameDto(getWinners(racingCarGame), racingCarGame.getCount()),
            carsToPlayerDtos(racingCarGame)
        );

        return new ResultResponseDto(getWinners(racingCarGame), getCars(racingCarGame));
    }

    private List<String> spiteNameWithComma(RacingGameRequestDto racingGameRequestDto) {
        return Arrays.stream(racingGameRequestDto.getNames().split(","))
            .map(String::trim)
            .collect(Collectors.toList());
    }

    public String getWinners(RacingCarGame racingCarGame) {
        return racingCarGame.findWinners().getAll().stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));
    }

    private List<PlayerDto> carsToPlayerDtos(RacingCarGame racingCarGame) {
        return racingCarGame.getCars().getAll().stream()
                .map(car -> new PlayerDto(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
    }

    public List<Car> getCars(RacingCarGame racingCarGame) {
        return racingCarGame.getCars().getAll();
    }
}
