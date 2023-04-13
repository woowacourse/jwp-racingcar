package racing.service;

import org.springframework.stereotype.Service;
import racing.controller.dto.request.CarRequest;
import racing.CarFactory;
import racing.RacingGameDao;
import racing.controller.dto.response.RacingCarStateResponse;
import racing.controller.dto.response.RacingGameResultResponse;
import racing.domain.Cars;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingGameService {

    private final RacingGameDao racingGameDao;

    public RacingGameService(RacingGameDao racingGameDao) {
        this.racingGameDao = racingGameDao;
    }

    public Cars createCars(String names) {
        return CarFactory.carFactory(names);
    }

    public void move(int randomNumber, Cars cars, int count) {
        for (int i = 0; i < count; i++) {
            cars.calculator(randomNumber);
        }
    }

    public Long createRacingGame(int count) {
        return racingGameDao.saveGame(count);
    }

    public void saveCarsState(Long gameId, Cars cars) {
        List<String> winners = cars.getWinners();

        cars.getCars().stream()
                .map(car -> CarRequest.of(gameId, car, winners.contains(car.getName())))
                .forEach(racingGameDao::saveCar);
    }

    public List<String> getWinners(Cars cars) {
        return cars.getWinners();
    }

    public RacingGameResultResponse getRacingGameResultResponse(Cars cars) {
        List<RacingCarStateResponse> racingCarsState = cars.getCars().stream()
                .map(car -> new RacingCarStateResponse(car.getName(), car.getStep()))
                .collect(Collectors.toList());

        return new RacingGameResultResponse(getWinners(cars), racingCarsState);
    }

}
