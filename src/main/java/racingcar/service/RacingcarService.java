package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.controller.RacingResponse;
import racingcar.dao.CarDao;
import racingcar.dao.CarEntity;
import racingcar.dao.RacingGameDao;
import racingcar.dao.RacingGameEntity;
import racingcar.dto.CarDto;
import racingcar.dto.RacingGameDto;
import racingcar.model.Car;
import racingcar.model.RacingGame;

@Service
public class RacingcarService {

    private static final int MINIMUM_PARTICIPANT = 2;

    private final RacingGameDao racingGameDao;
    private final CarDao carDao;

    public RacingcarService(final RacingGameDao racingGameDao, final CarDao carDao) {
        this.racingGameDao = racingGameDao;
        this.carDao = carDao;
    }

    public RacingResponse move(final List<String> carNames, final int count) {
        List<Car> cars = getCars(carNames);

        RacingGame racingGame = insertRacingGame(cars, count);

        insertCar(cars, racingGame);

        racingGame.moveAllCars();
        List<Car> winners = racingGame.findWinners();

        return new RacingResponse(winners, cars);
    }

    private RacingGame insertRacingGame(final List<Car> cars, final int count) {
        RacingGameEntity savedRacingGameEntity = racingGameDao.insertRacingGame(RacingGameDto.from(count));
        return new RacingGame(savedRacingGameEntity.getId(), cars, count);
    }

    private void insertCar(final List<Car> cars, final RacingGame racingGame) {
        for (Car car : cars) {
            CarDto carDto = makeCarDto(car);
            carDao.insertCar(carDto, racingGame.getId());
        }
    }

    private List<Car> getCars(final List<String> carNames) {
        if (carNames.size() < MINIMUM_PARTICIPANT) {
            throw new IllegalArgumentException("[ERROR] 경주는 최소 " + MINIMUM_PARTICIPANT + "명이 필요해요.");
        }
        return CarFactory.makeCars(carNames);
    }

    public List<RacingResponse> allResults() {
        List<RacingGame> results = new ArrayList<>();
        List<RacingGameEntity> racingGameEntities = racingGameDao.selectAllResults();
        for (RacingGameEntity racingGameEntity : racingGameEntities) {
            results.add(
                    new RacingGame(
                            carDao.findCarsByRacingGameId(racingGameEntity.getId()).stream()
                                    .map(this::makeCar)
                                    .collect(Collectors.toList()),
                            racingGameEntity.getId())
            );
        }
        return makeRacingResponses(results);
    }

    private List<RacingResponse> makeRacingResponses(final List<RacingGame> games) {
        return games.stream()
                .map(game -> new RacingResponse(game.findWinners(), game.getCars()))
                .collect(Collectors.toList());

    }

    private Car makeCar(final CarEntity carEntity) {
        return new Car(carEntity.getName(), carEntity.getPosition());
    }

    private CarDto makeCarDto(final Car car) {
        return CarDto.of(car.getName(), car.getPosition());
    }


}
