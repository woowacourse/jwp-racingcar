package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.RandomNumberGenerator;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.dto.request.CarGameRequest;
import racingcar.dto.response.CarResponse;
import racingcar.dto.response.GameResponse;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;
import racingcar.mapper.CarMapper;
import racingcar.mapper.GameMapper;
import racingcar.mapper.WinnerMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {
    private final CarMapper carMapper;
    private final GameMapper gameMapper;
    private final WinnerMapper winnerMapper;
    private final RandomNumberGenerator numberGenerator;

    public RacingGameService(CarMapper carMapper, GameMapper gameMapper, WinnerMapper winnerMapper, RandomNumberGenerator numberGenerator) {
        this.carMapper = carMapper;
        this.gameMapper = gameMapper;
        this.winnerMapper = winnerMapper;
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
        GameEntity save = gameMapper.save(GameEntity.from(tryCount));

        List<CarEntity> collect = game.getCars().stream().map(car -> carMapper.save(
                CarEntity.of(save.getId(), car.getName(), car.getPosition()))
        ).collect(Collectors.toList());

        saveWinners(collect);
    }

    private void saveWinners(List<CarEntity> collect) {
        List<CarEntity> winners = getWinners(collect);
        winners.forEach(winnerMapper::save);
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
        return gameMapper.findAll().stream()
                .map(game -> {
                    List<CarEntity> winners = carMapper.findWinnersByGameId(game.getId());
                    List<CarEntity> cars = carMapper.findAllByGameId(game.getId());
                    return GameResponse.of(winners, cars);
                })
                .collect(Collectors.toList());
    }
}
