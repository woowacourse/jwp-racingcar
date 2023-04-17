package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import racingcar.controller.dto.GameInfoRequest;
import racingcar.controller.dto.RaceResultResponse;
import racingcar.dao.raceresult.dto.RaceResultRegisterRequest;
import racingcar.domain.Car;
import racingcar.domain.RacingCars;

@Service
public class RaceService {

    private final CarService carService;
    private final RaceResultService raceResultService;

    public RaceService(final CarService carService,
                       final RaceResultService raceResultService) {
        this.carService = carService;
        this.raceResultService = raceResultService;
    }

    public int saveRaceResult(final GameInfoRequest gameInfoRequest) {
        RacingCars carsAfterMove = carService.race(gameInfoRequest);
        RaceResultRegisterRequest raceResultRegisterRequest =
                RaceResultRegisterRequest.create(gameInfoRequest.getCount(), carsAfterMove);
        int savedPlayResultId = raceResultService.generate(raceResultRegisterRequest);
        carService.registerCars(carsAfterMove, savedPlayResultId);
        return savedPlayResultId;
    }

    public RaceResultResponse createRaceResult(final int playResultId) {
        String winners = raceResultService.searchWinners(playResultId);
        List<Car> findCars = carService.getAllCars(playResultId);
        return RaceResultResponse.create(winners, findCars);
    }

    public List<RaceResultResponse> searchAllRaceResult() {
        List<Integer> playResultIds = raceResultService.searchPlayResultIds();
        return playResultIds.stream()
                .map(this::createRaceResult)
                .collect(Collectors.toUnmodifiableList());
    }
}
