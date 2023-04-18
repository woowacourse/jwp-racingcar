package racing.service;

import org.springframework.stereotype.Service;
import racing.controller.dto.response.CarResponse;
import racing.dao.CarEntity;
import racing.controller.dto.response.RacingGameResultResponse;
import racing.dao.CarDao;
import racing.dao.GameDao;
import racing.domain.CarFactory;
import racing.controller.dto.request.CarRequest;
import racing.domain.Car;
import racing.domain.Cars;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class RacingGameService {

    private final GameDao gameDao;
    private final CarDao carDao;

    public RacingGameService(GameDao gameDao, CarDao carDao) {
        this.gameDao = gameDao;
        this.carDao = carDao;
    }

    public Cars createCars(String names) {
        return CarFactory.carFactory(names);
    }

    public void move(int randomNumber, Car car) {
        if (randomNumber >= 4) {
            car.move();
        }

    }

    public Long createRacingGame(int count) {
        return gameDao.saveGame(count);
    }

    public RacingGameResultResponse saveCarsState(Long gameId, Cars cars) {
        List<String> winners = cars.getWinners();

        cars.getCars().stream()
                .map(car -> CarRequest.of(gameId, car, winners.contains(car.getName())))
                .forEach(carDao::saveCar);
        return RacingGameResultResponse.of(winners, cars);
    }

    public List<RacingGameResultResponse> getRacingGameResultResponse() {
        List<CarEntity> cars = carDao.findAll();
        final Map<Long, List<CarEntity>> carEntityResponsesByGameId = cars.stream()
                .collect(Collectors.groupingBy(CarEntity::getGameId));

        return carEntityResponsesByGameId.values().stream()
                .map(this::getResultResponse)
                .collect(toList());
    }

    private RacingGameResultResponse getResultResponse(List<CarEntity> carEntityResponses) {
        List<String> winners = carEntityResponses.stream()
                .filter(CarEntity::isWinner)
                .map(CarEntity::getName)
                .collect(toList());

        List<CarResponse> carResponses = carEntityResponses.stream()
                .map(carEntityResponse -> new CarResponse(carEntityResponse.getName(), carEntityResponse.getStep()))
                .collect(toList());

        return new RacingGameResultResponse(winners, carResponses);
    }

}
