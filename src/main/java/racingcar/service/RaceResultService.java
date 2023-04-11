package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.controller.dto.CarStatusResponse;
import racingcar.controller.dto.GameInfoRequest;
import racingcar.controller.dto.RaceResultResponse;
import racingcar.dao.car.CarDao;
import racingcar.dao.car.dto.CarRegisterRequest;
import racingcar.dao.raceresult.RaceResultDao;
import racingcar.dao.raceresult.dto.RaceResultRegisterRequest;
import racingcar.domain.Car;
import racingcar.domain.RacingCars;
import racingcar.util.NumberGenerator;
import racingcar.view.InputViewValidator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaceResultService {

    private static final String CAR_NAMES_DELIMITER = ",";
    private static final int START_POSITION = 0;

    private final RaceResultDao raceResultDao;
    private final CarDao carDao;
    private final InputViewValidator inputViewValidator = new InputViewValidator();
    private final NumberGenerator numberGenerator;

    public RaceResultService(final RaceResultDao raceResultDao, final CarDao carDao,
                             final NumberGenerator numberGenerator) {
        this.raceResultDao = raceResultDao;
        this.carDao = carDao;
        this.numberGenerator = numberGenerator;
    }

    public RaceResultResponse searchRaceResult(final GameInfoRequest gameInfoRequest) {

        final String names = gameInfoRequest.getNames();

        inputViewValidator.validateCarNames(names);
        String[] splitCarNames = getSplitCarNames(names);
        inputViewValidator.validateSplitCarNames(splitCarNames);

        RacingCars racingCars = generateRacingCarsStep(splitCarNames);

        final int tryCount = gameInfoRequest.getCount();

        racingCars.moveAllCars(tryCount, numberGenerator);

        // db에 winner 저장하는 mapper
        final String joinedWinners = findWinners(racingCars).stream()
                                                            .collect(Collectors.joining(","));

        //DB에 저장하는 DTO
        final RaceResultRegisterRequest raceResultRegisterRequest = new RaceResultRegisterRequest(tryCount,
                                                                                                  joinedWinners);

        //저장된 ResultId
        final int savedId = raceResultDao.save(raceResultRegisterRequest);

        //Car 정보 저장
        racingCars.getCars()
                  .forEach(car -> carDao.save(new CarRegisterRequest(car.getName(), car.getPosition(), savedId)));

        //경기 결과
        final List<CarStatusResponse> carStatusResponses =
                racingCars.getCars()
                          .stream()
                          .map(car -> new CarStatusResponse(car.getName(),
                                                            car.getPosition()))
                          .collect(Collectors.toList());

        return new RaceResultResponse(findWinners(racingCars), carStatusResponses);
    }

    private RacingCars generateRacingCarsStep(String[] carNames) {
        List<Car> cars = generateCars(carNames);
        return new RacingCars(cars);
    }

    private String[] getSplitCarNames(String carNames) {
        return carNames.split(CAR_NAMES_DELIMITER, -1);
    }

    private List<Car> generateCars(String[] carNames) {
        return Arrays.stream(carNames)
                     .map(carName -> new Car(carName, START_POSITION))
                     .collect(Collectors.toUnmodifiableList());
    }

    private List<String> findWinners(RacingCars racingCars) {
        return racingCars.getWinners()
                         .stream()
                         .map(Car::getName)
                         .collect(Collectors.toList());
    }
}
