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
import racingcar.service.mapper.RaceResultMapper;
import racingcar.util.NumberGenerator;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaceResultService {


    private final RaceResultDao raceResultDao;
    private final CarDao carDao;
    private final NumberGenerator numberGenerator;
    private final RaceResultMapper raceResultMapper;

    public RaceResultService(final RaceResultDao raceResultDao, final CarDao carDao,
                             final NumberGenerator numberGenerator, final RaceResultMapper raceResultMapper) {
        this.raceResultDao = raceResultDao;
        this.carDao = carDao;
        this.numberGenerator = numberGenerator;
        this.raceResultMapper = raceResultMapper;
    }

    public RaceResultResponse searchRaceResult(final GameInfoRequest gameInfoRequest) {

        final String names = gameInfoRequest.getNames();
        final RacingCars racingCars = RacingCars.makeCars(names);

        final int tryCount = gameInfoRequest.getCount();

        racingCars.moveAllCars(tryCount, numberGenerator);

        final RaceResultRegisterRequest raceResultRegisterRequest = raceResultMapper.convertToRaceResult(tryCount,
                                                                                                         racingCars);

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

        return new RaceResultResponse(raceResultRegisterRequest.getWinners(), carStatusResponses);
    }
}
