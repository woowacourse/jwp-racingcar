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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaceResultService {


    private final RaceResultDao raceResultDao;
    private final CarDao carDao;
    private final NumberGenerator numberGenerator;

    public RaceResultService(final RaceResultDao raceResultDao, final CarDao carDao,
                             final NumberGenerator numberGenerator) {
        this.raceResultDao = raceResultDao;
        this.carDao = carDao;
        this.numberGenerator = numberGenerator;
    }

    public RaceResultResponse searchRaceResult(final GameInfoRequest gameInfoRequest) {

        final String names = gameInfoRequest.getNames();
        final RacingCars racingCars = RacingCars.makeCars(names);
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

    private List<String> findWinners(RacingCars racingCars) {
        return racingCars.getWinners()
                         .stream()
                         .map(Car::getName)
                         .collect(Collectors.toList());
    }
}
