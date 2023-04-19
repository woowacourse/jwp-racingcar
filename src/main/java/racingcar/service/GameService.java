package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import racingcar.domain.Car;
import racingcar.domain.Game;
import racingcar.domain.MoveChance;
import racingcar.domain.RandomMoveChance;
import racingcar.repository.GameRepository;
import racingcar.service.dto.CarDto;
import racingcar.service.dto.ResultDto;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final MoveChance moveChance;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        this.moveChance = RandomMoveChance.getInstance();
    }

    public ResultDto playWith(List<String> names, int trialCount) {
        Game game = createGameWith(names, trialCount);
        while (game.isInProgress()) {
            game.playOnceWith(moveChance);
        }

        gameRepository.save(game);
        return createDtoOf(game);
    }

    public List<ResultDto> getAllResults() {
        List<ResultDto> results = new ArrayList<>();
        List<Game> games = gameRepository.findAll();
        for (Game game : games) {
            results.add(createDtoOf(game));
        }
        return results;
    }

    private Game createGameWith(List<String> names, int trialCount) {
        return new Game(makeCarsWith(names), trialCount);
    }

    private List<Car> makeCarsWith(List<String> carNames) {
        return carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    private ResultDto createDtoOf(Game game) {
        return new ResultDto(
                getNamesOf(game.findWinners()),
                createDtosOf(game.getCars())
        );
    }

    private List<String> getNamesOf(List<Car> cars) {
        return cars.stream()
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    private List<CarDto> createDtosOf(List<Car> cars) {
        return cars.stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
    }
}
