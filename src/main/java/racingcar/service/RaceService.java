package racingcar.service;

import java.util.List;

import org.springframework.stereotype.Service;
import racingcar.controller.dto.GameInfoRequest;
import racingcar.controller.dto.RaceResultResponse;
import racingcar.dao.raceresult.RaceResultDao;
import racingcar.dao.raceresult.dto.RaceResultRegisterRequest;
import racingcar.domain.Car;
import racingcar.domain.RacingCars;
import racingcar.service.mapper.RaceResultMapper;

@Service
public class RaceService {

    private final CarService carService;
    private final RaceResultService raceResultService;
    private final RaceResultMapper raceResultMapper;

    public RaceService(final CarService carService,
                       RaceResultService raceResultService, final RaceResultMapper raceResultMapper) {
        this.carService = carService;
        this.raceResultService = raceResultService;
        this.raceResultMapper = raceResultMapper;
    }

    public int saveRaceResult(final GameInfoRequest gameInfoRequest) {
        RacingCars carsAfterMove = carService.race(gameInfoRequest);
        RaceResultRegisterRequest raceResultRegisterRequest
                = raceResultMapper.mapToRaceResult(gameInfoRequest.getCount(), carsAfterMove);
        int savedPlayResultId = raceResultService.generate(raceResultRegisterRequest);
        carService.registerCars(carsAfterMove, savedPlayResultId);
        return savedPlayResultId;
    }

    public RaceResultResponse createRaceResult(final int playResultId) {
        String winners = raceResultService.searchWinners(playResultId);
        List<Car> findCars = carService.getAllCars(playResultId);
        return new RaceResultResponse(winners, raceResultMapper.mapToCarStatus(findCars));
    }
}
