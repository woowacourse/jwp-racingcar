package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.entity.RaceResultEntity;
import racingcar.service.dto.CarStatusResponse;
import racingcar.service.dto.GameInfoRequest;
import racingcar.service.dto.RaceResultResponse;
import racingcar.dao.raceresult.RaceResultDao;
import racingcar.domain.Car;
import racingcar.domain.RacingCars;
import racingcar.service.mapper.RaceResultMapper;
import racingcar.util.NumberGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RaceResultService {

    private final RaceResultDao raceResultDao;
    private final CarService carService;
    private final NumberGenerator numberGenerator;
    private final RaceResultMapper raceResultMapper;

    public RaceResultService(final RaceResultDao raceResultDao, final CarService carService,
                             final NumberGenerator numberGenerator, final RaceResultMapper raceResultMapper) {
        this.raceResultDao = raceResultDao;
        this.carService = carService;
        this.numberGenerator = numberGenerator;
        this.raceResultMapper = raceResultMapper;
    }

    @Transactional
    public RaceResultResponse createRaceResult(final GameInfoRequest gameInfoRequest) {

        final String names = gameInfoRequest.getNames();
        final int tryCount = gameInfoRequest.getCount();

        final RacingCars racingCars = moveCars(names, tryCount);

        final RaceResultEntity raceResultEntity = raceResultMapper.mapToRaceResult(tryCount, racingCars);

        final int savedId = raceResultDao.save(raceResultEntity);

        carService.registerCars(racingCars, savedId);

        final List<CarStatusResponse> carStatusResponses = raceResultMapper.mapToCarStatus(racingCars);

        return new RaceResultResponse(raceResultEntity.getWinners(), carStatusResponses);
    }

    private RacingCars moveCars(final String names, final int tryCount) {
        final RacingCars racingCars = RacingCars.makeCars(names);
        racingCars.moveAllCars(tryCount, numberGenerator);
        return racingCars;
    }

    public List<RaceResultResponse> searchRaceResult() {

        final Map<String, List<Car>> allRaceResult = raceResultDao.findAllRaceResult();

        return allRaceResult.entrySet()
                            .stream()
                            .map(it -> new RaceResultResponse(it.getKey(),
                                                              raceResultMapper.mapToCarStatus(it.getValue())))
                            .collect(Collectors.toList());
    }
}
