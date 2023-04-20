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
import racingcar.exception.BadRequestException;
import racingcar.model.Car;
import racingcar.model.Cars;
import racingcar.strategy.RacingNumberGenerator;
import racingcar.model.Round;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final RacingNumberGenerator generator;
    private final PlayerDao playerDao;
    private final PlayResultDao playResultDao;

    public CarService(final RacingNumberGenerator generator, final PlayerDao playerDao, final PlayResultDao playResultDao) {
        this.generator = generator;
        this.playerDao = playerDao;
        this.playResultDao = playResultDao;
    }

    public WinnerCarDto playGame(final GameDto gameDto) {
        final Cars cars = initGame(gameDto);
        final Car winner = cars.getWinner();

        final WinnerCarDto winnerCarDto =
                new WinnerCarDto(convertToCarDto(cars.findWinnerCars(winner)), convertToCarDto(cars.findCars()));
        save(winner, winnerCarDto);
        return winnerCarDto;
    }

    private Cars initGame(final GameDto gameDto) {
        final Cars cars = generateCars(gameDto.getNames());
        Round round = new Round(gameDto.getCount());

        race(cars, round);
        return cars;
    }

    public Cars generateCars(String inputCarsName) {
        String[] carsName = inputCarsName.split(",");
        checkDuplication(carsName);
        return new Cars(mapToCars(carsName));
    }

    private List<Car> mapToCars(String[] carsName) {
        return Arrays.stream(carsName)
                .map(Car::new)
                .collect(Collectors.toUnmodifiableList());
    }

    private void race(final Cars cars, final Round round) {
        int count = round.getRound();
        while (count-- > 0) {
            cars.race(generator);
        }
    }

    private void checkDuplication(String[] carsName) {
        if (getDistinctCarsCount(carsName) != carsName.length) {
            throw new BadRequestException("이름은 중복될 수 없습니다.");
        }
    }

    private long getDistinctCarsCount(String[] carsName) {
        return Arrays.stream(carsName)
                .distinct()
                .count();
    }

    private List<CarDto> convertToCarDto(final List<Car> cars) {
        return cars.stream().
                map(car -> new CarDto(car.getName(), car.getPosition())).
                collect(Collectors.toList());
    }

    private void save(final Car winner, final WinnerCarDto winnerCarDto) {
        final long id = insertPlayResult();
        insertPlayers(id, winner, winnerCarDto);
    }

    private void insertPlayers(final long playResultId, final Car winner, final WinnerCarDto winnerCarDto) {
        winnerCarDto.getRacingCars()
                .forEach((carDto -> playerDao.insert(playResultId,
                        carDto.getName(),
                        carDto.getPosition(),
                        isWinner(winner.getPosition(), carDto.getPosition()))));
    }

    private boolean isWinner(int winnerPosition, int comparePosition) {
        return winnerPosition == comparePosition;
    }

    private long insertPlayResult() {
        return playResultDao.insert();
    }

    public List<GameResponse> findPlaysHistory() {
        final List<GameResponse> gameResponses = new ArrayList<>();
        final List<PlayResult> allPlayResult = playResultDao.findAllPlayResult();
        final List<List<Player>> allPlayer = findAllPlayer(allPlayResult);

        for (int index = 0; index < allPlayResult.size(); index++) {
            gameResponses.add(new GameResponse(getWinners(allPlayer.get(index)),
                    convertToCarDto(allPlayer, index)));
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

    private List<CarDto> convertToCarDto(final List<List<Player>> allPlayer, final int index) {
        return allPlayer.get(index).stream()
                .map(player -> new CarDto(player.getName(), player.getPosition())).collect(Collectors.toList());
    }
}
