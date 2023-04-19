package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import racingcar.controller.dto.GameInfoRequest;
import racingcar.controller.dto.RaceResultResponse;
import racingcar.dao.raceresult.dto.RaceResultRegisterRequest;
import racingcar.domain.Car;
import racingcar.domain.RacingCars;
import racingcar.repository.CarRepository;
import racingcar.repository.RaceResultRepository;
import racingcar.util.NumberGenerator;

@Service
public class RaceService {

    private final CarRepository carRepository;
    private final RaceResultRepository raceResultRepository;
    private final NumberGenerator numberGenerator;

    public RaceService(final CarRepository carRepository,
                       final RaceResultRepository raceResultRepository, NumberGenerator numberGenerator) {
        this.carRepository = carRepository;
        this.raceResultRepository = raceResultRepository;
        this.numberGenerator = numberGenerator;
    }

    public int saveRaceResult(final GameInfoRequest gameInfoRequest) {
        RacingCars carsAfterMove = race(gameInfoRequest);
        RaceResultRegisterRequest raceResultRegisterRequest =
                RaceResultRegisterRequest.create(gameInfoRequest.getCount(), carsAfterMove);
        int savedPlayResultId = raceResultRepository.save(raceResultRegisterRequest);
        carRepository.saveCars(carsAfterMove, savedPlayResultId);
        return savedPlayResultId;
    }

    private RacingCars race(final GameInfoRequest gameInfoRequest) {
        final String names = gameInfoRequest.getNames();
        final RacingCars racingCars = RacingCars.makeCars(names);

        final int tryCount = gameInfoRequest.getCount();

        racingCars.moveAllCars(tryCount, numberGenerator);

        return racingCars;
    }

    public RaceResultResponse createRaceResult(final int playResultId) {
        String winners = raceResultRepository.findWinnersByPlayResultId(playResultId);
        List<Car> findCars = carRepository.findAllByPlayResultId(playResultId);
        return RaceResultResponse.create(winners, findCars);
    }

    public List<RaceResultResponse> searchAllRaceResult() {
        List<Integer> playResultIds = raceResultRepository.findAllPlayResultId();
        return playResultIds.stream()
                .map(this::createRaceResult)
                .collect(Collectors.toUnmodifiableList());
    }
}
