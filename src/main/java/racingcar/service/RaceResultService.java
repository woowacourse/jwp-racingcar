package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.controller.dto.CarStatusResponse;
import racingcar.controller.dto.GameInfoRequest;
import racingcar.controller.dto.RaceResultResponse;
import racingcar.dao.raceresult.RaceResultDao;
import racingcar.dao.raceresult.dto.RaceResultRegisterRequest;
import racingcar.domain.RacingCars;
import racingcar.service.mapper.RaceResultMapper;
import racingcar.util.NumberGenerator;

import java.util.List;

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

    public RaceResultResponse createRaceResult(final GameInfoRequest gameInfoRequest) {

        final String names = gameInfoRequest.getNames();
        final RacingCars racingCars = RacingCars.makeCars(names);

        final int tryCount = gameInfoRequest.getCount();

        racingCars.moveAllCars(tryCount, numberGenerator);

        final RaceResultRegisterRequest raceResultRegisterRequest =
                raceResultMapper.mapToRaceResult(tryCount, racingCars);

        final int savedId = raceResultDao.save(raceResultRegisterRequest);

        carService.registerCars(racingCars, savedId);

        final List<CarStatusResponse> carStatusResponses = raceResultMapper.mapToCarStatus(racingCars);

        return new RaceResultResponse(raceResultRegisterRequest.getWinners(), carStatusResponses);
    }
}
