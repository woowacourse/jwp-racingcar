package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.PlayResultDao;
import racingcar.dao.PlayerDao;
import racingcar.dto.CarDto;
import racingcar.dto.GameDto;
import racingcar.dto.GameResponse;
import racingcar.dto.WinnerCarDto;
import racingcar.entity.PlayResult;
import racingcar.entity.Player;
import racingcar.model.Car;
import racingcar.model.RacingGame;
import racingcar.strategy.RacingRandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final PlayerDao playerDao;
    private final PlayResultDao playResultDao;

    public CarService(final PlayerDao playerDao, final PlayResultDao playResultDao) {
        this.playerDao = playerDao;
        this.playResultDao = playResultDao;
    }

    public WinnerCarDto playGame(final GameDto gameDto) {
        final RacingGame racingGame = new RacingGame(gameDto.getNames(), gameDto.getCount(),
                new RacingRandomNumberGenerator());
        racingGame.play();

        return save(racingGame);
    }

    private WinnerCarDto save(final RacingGame racingGame) {
        final long id = insertPlayResult();
        final List<Car> winnerCars = racingGame.getWinnerCars();
        final List<Car> cars = racingGame.getCars();


        for (Car car : cars) {
            System.out.println(winnerCars.contains(car));
        }

        cars.forEach(car -> playerDao.insert(id, car.getName(), car.getPosition(), winnerCars.contains(car)));
        return new WinnerCarDto(convertCarToCarDto(winnerCars), convertCarToCarDto(cars));
    }

    private long insertPlayResult() {
        return playResultDao.insert();
    }

    private List<CarDto> convertCarToCarDto(final List<Car> cars) {
        return cars.stream().
                map(car -> new CarDto(car.getName(), car.getPosition())).
                collect(Collectors.toList());
    }

    public List<GameResponse> findPlayHistories() {
        final List<GameResponse> gameResponses = new ArrayList<>();
        final List<PlayResult> allPlayResult = playResultDao.findAllPlayResult();
        final List<List<Player>> allPlayer = findAllPlayer(allPlayResult);

        for (int index = 0; index < allPlayResult.size(); index++) {
            gameResponses.add(new GameResponse(getWinners(allPlayer.get(index)),
                    convertPlayerToCarDto(allPlayer.get(index))));
        }
        return gameResponses;
    }

    private List<List<Player>> findAllPlayer(final List<PlayResult> allPlayResult) {
        return allPlayResult.stream()
                .map(playResult -> playerDao.findAllPlayer(playResult.getId()))
                .collect(Collectors.toList());
    }

    private String getWinners(final List<Player> allPlayer) {
        return allPlayer.stream()
                .filter(Player::isWinner)
                .map(Player::getName)
                .collect(Collectors.joining(","));
    }

    private List<CarDto> convertPlayerToCarDto(final List<Player> allPlayer) {
        return allPlayer.stream()
                .map(player -> new CarDto(player.getName(), player.getPosition())).
                collect(Collectors.toList());
    }
}
