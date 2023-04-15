package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.entity.Game;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.dto.PlayerDto;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.ResultResponseDto;
import racingcar.repository.CarRepository;
import racingcar.repository.GameRepository;
import racingcar.util.NumberGenerator;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingCarService {

    private final NumberGenerator numberGenerator;
    private final CarRepository carRepository;
    private final GameRepository gameRepository;

    public RacingCarService(NumberGenerator numberGenerator, CarRepository carRepository, GameRepository gameRepository) {
        this.numberGenerator = numberGenerator;
        this.carRepository = carRepository;
        this.gameRepository = gameRepository;
    }

    public ResultResponseDto play(RacingGameRequestDto racingGameRequestDto) {
        Long gameId = createGame();

        Cars cars = Cars.of(racingGameRequestDto.getNames(), gameId);
        move(cars, racingGameRequestDto.getCount());
        String winners = getWinners(cars);

        Game savedGame = gameRepository.save(new Game(gameId, racingGameRequestDto.getCount(), winners));
        Cars savedCars = carRepository.saveAll(cars);

        List<PlayerDto> playerDtos = mapToPlayerDtos(savedCars);

        return new ResultResponseDto(savedGame.getWinners(), playerDtos);
    }

    private Long createGame() {
        Game game = gameRepository.save(new Game());
        return game.getGameId();
    }

    private void move(Cars cars, int count) {
        for (int i = 0; i < count; i++) {
            cars.moveAll(numberGenerator);
        }
    }

    private List<PlayerDto> mapToPlayerDtos(Cars cars) {
        return cars.getCars().stream()
                .map(car -> new PlayerDto(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
    }

    private String getWinners(Cars cars) {
        return cars.pickWinners().getCars().stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));
    }
}
