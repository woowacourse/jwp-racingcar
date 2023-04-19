package racingcar.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.dao.RacingGameDao;
import racingcar.dto.CarDto;
import racingcar.dto.RacingGameDto;
import racingcar.dto.RacingResponse;
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

        for (int i = 1; i <= count; i++) {
            moveAllCars(cars);
        }
        List<Car> winners = findWinners(cars);

        RacingGame racingGame = insertRacingGame(count);

        insertCar(cars, racingGame);

        List<CarDto> winnerCarsDtos = makeCarsDtos(winners);
        List<CarDto> carsDtos = makeCarsDtos(cars);

        return new RacingResponse(winnerCarsDtos, carsDtos);
    }

    private RacingGame insertRacingGame(final int count) {
        RacingGameDto racingGameDto = racingGameDao.insertRacingGame(RacingGameDto.from(count));
        return new RacingGame(racingGameDto.getId(), count);
    }

    private void insertCar(final List<Car> cars, final RacingGame racingGame) {
        for (Car car : cars) {
            CarDto carDto = makeCarDto(car);
            carDao.insertCar(carDto, racingGame.getId());
        }
    }

    private void moveAllCars(final List<Car> cars) {
        for (Car car : cars) {
            car.move(RandomMaker.random());
        }
    }

    private List<Car> getCars(final List<String> carNames) {
        if (carNames.size() < MINIMUM_PARTICIPANT) {
            throw new IllegalArgumentException("[ERROR] 경주는 최소 " + MINIMUM_PARTICIPANT + "명이 필요해요.");
        }
        return CarFactory.makeCars(carNames);
    }

    private List<Car> findWinners(final List<Car> cars) {
        int winnerPosition = findPosition(cars);

        return cars.stream()
                .filter(car -> car.isPosition(winnerPosition))
                .collect(Collectors.toUnmodifiableList());
    }

    private int findPosition(final List<Car> cars) {
        int maxPosition = 0;

        for (Car car : cars) {
            maxPosition = car.findGreaterPosition(maxPosition);
        }

        return maxPosition;
    }

    public List<RacingResponse> allResults() {
        Map<Integer, List<CarDto>> results = new LinkedHashMap<>();
        List<RacingGameDto> racingGameDtos = racingGameDao.selectAllResults();
        for (RacingGameDto racingGameDto : racingGameDtos) {
            results.put(racingGameDto.getId(), carDao.findCarsByRacingGameId(racingGameDto.getId()));
        }
        return makeRacingResponses(results);
    }

    private List<RacingResponse> makeRacingResponses(final Map<Integer, List<CarDto>> results) {
        return results.values().stream()
                .map(carDtos -> {
                    List<Car> findCars = carDtos.stream()
                            .map(this::makeCar)
                            .collect(Collectors.toList());
                    List<Car> winners = findWinners(findCars);
                    List<CarDto> findWinnerCarsDtos = makeCarsDtos(winners);
                    List<CarDto> findCarsDtos = makeCarsDtos(findCars);
                    return new RacingResponse(findWinnerCarsDtos, findCarsDtos);
                })
                .collect(Collectors.toList());
    }

    private Car makeCar(final CarDto carDto) {
        return new Car(carDto.getName(), carDto.getPosition());
    }

    private CarDto makeCarDto(final Car car) {
        return CarDto.of(car.getName(), car.getPosition());
    }

    private List<CarDto> makeCarsDtos(final List<Car> cars) {
        return cars.stream()
                .map(this::makeCarDto)
                .collect(Collectors.toList());
    }

}
