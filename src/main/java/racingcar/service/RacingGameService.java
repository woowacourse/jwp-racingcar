package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.RandomNumberGenerator;
import racingcar.api.dto.request.CarGameRequest;
import racingcar.api.dto.response.CarResponse;
import racingcar.api.dto.response.GameResponse;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;
import racingcar.repository.CarRepository;
import racingcar.repository.GameRepository;
import racingcar.repository.WinnerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {
    private final CarRepository carRepository;
    private final GameRepository gameRepository;
    private final WinnerRepository winnerRepository;
    private final RandomNumberGenerator numberGenerator;

    public RacingGameService(CarRepository carRepository, GameRepository gameRepository, WinnerRepository winnerRepository, RandomNumberGenerator numberGenerator) {
        this.carRepository = carRepository;
        this.gameRepository = gameRepository;
        this.winnerRepository = winnerRepository;
        this.numberGenerator = numberGenerator;
    }

    public GameResponse play(CarGameRequest request) {
        RacingGame game = new RacingGame(numberGenerator, Cars.from(request), request.getCount());
        int tryCount = game.getTryCount();

        progress(game);
        saveGame(game, tryCount);

        List<CarResponse> cars = getCars(game);
        String winners = getWinners(game);
        return GameResponse.of(winners, cars);
    }

    private List<CarResponse> getCars(RacingGame game) {
        return game.getCars().stream()
                .map(CarResponse::from)
                .collect(Collectors.toList());
    }

    private String getWinners(RacingGame game) {
        return game.decideWinners().stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));
    }

    private void saveGame(RacingGame game, int tryCount) {
        GameEntity save = gameRepository.save(GameEntity.from(tryCount));

        if (save == null) {
            return;
        }

        List<CarEntity> collect = game.getCars().stream().map(car -> carRepository.save(
                CarEntity.of(save.getId(), car.getName(), car.getPosition()))
        ).collect(Collectors.toList());

        saveWinners(collect);
    }

    private void saveWinners(List<CarEntity> collect) {
        List<CarEntity> winners = getWinners(collect);
        winners.forEach(winnerRepository::save);
    }

    private static List<CarEntity> getWinners(List<CarEntity> collect) {
        int maxPosition = collect.stream()
                .mapToInt(CarEntity::getPosition)
                .max()
                .orElseThrow();

        return collect.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .collect(Collectors.toUnmodifiableList());
    }

    private static List<CarResponse> getCarResponses(Cars cars) {
        return cars.getUnmodifiableCars()
                .stream()
                .map(CarResponse::from)
                .collect(Collectors.toList());
    }

    private void progress(RacingGame game) {
        while (!game.isEnd()) {
            game.play();
        }
    }

    public List<GameResponse> findAllGame() {
        return gameRepository.findAll().stream()
                .map(game -> {
                    List<CarEntity> winners = carRepository.findWinnersByGameId(game.getId());
                    List<CarEntity> cars = carRepository.findAllByGameId(game.getId());
                    return GameResponse.of(winners, cars);
                })
                .collect(Collectors.toList());
    }
}
