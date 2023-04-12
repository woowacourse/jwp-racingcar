package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dao.CarDao;
import racingcar.dao.RacingGameDao;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.dto.CarDto;
import racingcar.dto.response.RacingCarResponse;
import racingcar.dto.response.RacingResultResponse;
import racingcar.dto.request.RacingStartRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WebRacingCarController {

    private final RacingGameDao racingGameDao;
    private final CarDao carDao;

    public WebRacingCarController(RacingGameDao racingGameDao, CarDao carDao) {
        this.racingGameDao = racingGameDao;
        this.carDao = carDao;
    }

    @PostMapping("/plays")
    public RacingResultResponse play(@RequestBody RacingStartRequest racingStartRequest) {
        Cars cars = generateCars(racingStartRequest.getNames());
        return playGame(cars, racingStartRequest.getCount());
    }

    private Cars generateCars(List<String> carNames) {
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        List<Car> carInstances = new ArrayList<>();
        for (String name : carNames) {
            carInstances.add(new Car(name, numberGenerator));
        }
        return new Cars(carInstances);
    }

    private RacingResultResponse playGame(Cars cars, int round) {
        RacingGame racingGame = new RacingGame(cars, round);
        while(!racingGame.isGameEnded()){
            racingGame.playOneRound();
        }

        saveGameResult(round, racingGame);

        return createRacingResultResponse(racingGame);
    }

    private void saveGameResult(int round, RacingGame racingGame) {
        int gameId = racingGameDao.save(round);
        carDao.save(createCarDtos(racingGame, gameId));
    }

    private List<CarDto> createCarDtos(RacingGame racingGame, int gameId) {
        List<CarDto> carDtos = new ArrayList<>();

        List<Car> winnerCars = racingGame.findWinnerCars();
        List<Car> cars = racingGame.getCars();

        for (Car car : cars) {
            carDtos.add(new CarDto(gameId, car.getName(), car.getPosition(), winnerCars.contains(car)));
        }

        return carDtos;
    }

    private RacingResultResponse createRacingResultResponse(RacingGame racingGame) {
        List<RacingCarResponse> racingCars = getRacingCarResponses(racingGame);
        String winners = getWinners(racingGame);

        return new RacingResultResponse(racingCars, winners);
    }

    private List<RacingCarResponse> getRacingCarResponses(RacingGame racingGame) {
        List<RacingCarResponse> racingCars = new ArrayList<>();

        for (Car car : racingGame.getCars()) {
            racingCars.add(new RacingCarResponse(car.getName(), car.getPosition()));
        }
        return racingCars;
    }

    private String getWinners(RacingGame racingGame) {
        return racingGame.findWinnerCars().stream()
                .map(Car::getName)
                .collect(Collectors.joining(", "));
    }
}
