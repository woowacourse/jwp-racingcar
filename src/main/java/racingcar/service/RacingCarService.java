package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.RacingCars;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingResultResponse;
import racingcar.repository.RacingCarRepository;
import racingcar.utils.NumberGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
public class RacingCarService {
    private final RacingCarRepository racingCarRepository;
    private final NumberGenerator numberGenerator;

    public RacingCarService(RacingCarRepository racingCarRepository, NumberGenerator numberGenerator) {
        this.racingCarRepository = racingCarRepository;
        this.numberGenerator = numberGenerator;
    }

    @Transactional
    public RacingResultResponse playRacingGame(List<String> carNames, int tryCount) {
        int gameId = racingCarRepository.saveGame(tryCount);
        RacingCars racingCars = createRacingCars(carNames);
        moveCars(racingCars, tryCount);
        racingCarRepository.saveCars(gameId, racingCars.getCars());
        racingCarRepository.saveWinners(gameId, racingCars.getWinners());
        return obtainRacingResult(gameId);
    }

    public RacingCars createRacingCars(List<String> carNames) {
        return carNames.stream()
                .map(Car::new)
                .collect(collectingAndThen(toList(), RacingCars::new));
    }

    public void moveCars(RacingCars racingCars, int tryCount) {
        for (int i = 0; i < tryCount; i++) {
            racingCars.moveCars(numberGenerator);
        }
    }

    @Transactional(readOnly = true)
    public RacingResultResponse obtainRacingResult(int gameId) {
        List<String> winners = racingCarRepository.findWinnersByGameId(gameId);
        List<RacingCarDto> racingCars = racingCarRepository.findRacingCarsByGameId(gameId);
        return new RacingResultResponse(winners, racingCars);
    }

    public List<RacingResultResponse> searchGameHistory() {
        Map<Integer, List<RacingCarDto>> racingCars = racingCarRepository.findRacingCars();
        Map<Integer, List<String>> winners = racingCarRepository.findWinners();
        List<RacingResultResponse> racingResultResponses = new ArrayList<>();
        racingCars.keySet().forEach(gameId -> racingResultResponses.add(new RacingResultResponse(winners.get(gameId),racingCars.get(gameId))));
        return racingResultResponses;
    }
}
