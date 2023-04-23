package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import racingcar.controller.dto.GameInfoRequest;
import racingcar.controller.dto.RaceResultResponse;
import racingcar.domain.Car;
import racingcar.domain.RacingCars;
import racingcar.repository.CarRepository;
import racingcar.repository.RaceResultRepository;
import racingcar.util.NumberGenerator;

@Service
public class WebRaceService extends RaceService {

    private final CarRepository carRepository;
    private final RaceResultRepository raceResultRepository;

    public WebRaceService(final NumberGenerator numberGenerator, final CarRepository carRepository,
                          final RaceResultRepository raceResultRepository) {
        super(numberGenerator);
        this.carRepository = carRepository;
        this.raceResultRepository = raceResultRepository;
    }

    public int saveRaceResult(final GameInfoRequest gameInfoRequest) {
        RacingCars carsAfterMove = race(gameInfoRequest);
        int savedPlayResultId = raceResultRepository.save(gameInfoRequest.getCount(), carsAfterMove);
        carRepository.saveCars(carsAfterMove, savedPlayResultId);
        return savedPlayResultId;
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
