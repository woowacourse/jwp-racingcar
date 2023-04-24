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
import racingcar.strategy.RacingNumberGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final RacingNumberGenerator racingNumberGenerator;
    private final PlayerDao playerDao;
    private final PlayResultDao playResultDao;

    public CarService(final RacingNumberGenerator racingNumberGenerator, final PlayerDao playerDao, final PlayResultDao playResultDao) {
        this.racingNumberGenerator = racingNumberGenerator;
        this.playerDao = playerDao;
        this.playResultDao = playResultDao;
    }

    public WinnerCarDto playGame(final GameDto gameDto) {
        final RacingGame racingGame = new RacingGame(gameDto.getNames(), gameDto.getCount(), racingNumberGenerator);
        racingGame.play();

        return save(racingGame);
    }

    private WinnerCarDto save(final RacingGame racingGame) {
        final long id = insertPlayResult();
        final List<Car> winnerCars = racingGame.getWinnerCars();
        final List<Car> cars = racingGame.getCars();
        final List<Player> players = mapToPlayers(id, winnerCars, cars);

        playerDao.insertAll(players);
        return new WinnerCarDto(CarDto.convertCarToCarDto(winnerCars), CarDto.convertCarToCarDto(cars));
    }

    private static List<Player> mapToPlayers(final long id, final List<Car> winnerCars, final List<Car> cars) {
        return cars.stream()
                .map(car -> new Player(id, car.getName(), car.getPosition(), winnerCars.contains(car)))
                .collect(Collectors.toList());
    }

    private long insertPlayResult() {
        return playResultDao.insert();
    }

    public List<GameResponse> findPlayHistories() {
        final List<GameResponse> gameResponses = new ArrayList<>();
        final List<PlayResult> allPlayResult = playResultDao.findAllPlayResult();
        final List<List<Player>> allPlayer = findAllPlayer(allPlayResult);

        for (int index = 0; index < allPlayResult.size(); index++) {
            gameResponses.add(new GameResponse(getWinners(allPlayer.get(index)),
                    CarDto.convertPlayerToCarDto(allPlayer.get(index))));
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
}
