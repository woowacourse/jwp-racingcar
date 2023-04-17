package racingcar.service;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.RacingCars;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingResultResponse;
import racingcar.repository.RacingCarRepository;
import racingcar.utils.NumberGenerator;

@Service
public class RacingCarService {
    private final RacingCarRepository racingCarRepository;
    private final NumberGenerator numberGenerator;

    public RacingCarService(RacingCarRepository racingCarRepository, NumberGenerator numberGenerator) {
        this.racingCarRepository = racingCarRepository;
        this.numberGenerator = numberGenerator;
    }

    @Transactional
    public int playRacingGame(List<String> carNames, int tryCount) {
        int gameId = racingCarRepository.saveGame(tryCount);
        RacingCars racingCars = carNames.stream()
                .map(Car::new)
                .collect(collectingAndThen(toList(), RacingCars::new));
        for (int i = 0; i < tryCount; i++) {
            racingCars.moveCars(numberGenerator);
        }
        racingCarRepository.saveCars(gameId, racingCars.getCars());
        racingCarRepository.saveWinners(gameId, racingCars.getWinners());
        return gameId;
    }

    @Transactional(readOnly = true)
    public RacingResultResponse obtainRacingResult(int gameId) {
        List<String> winners = racingCarRepository.findWinnersByGameId(gameId);
        List<RacingCarDto> racingCars = racingCarRepository.findRacingCarsByGameId(gameId);
        return new RacingResultResponse(winners, racingCars);
    }

    public List<RacingResultResponse> searchGameHistory() {
        Map<Integer, List<RacingCarDto>> playerHistory = racingCarRepository.findRacingCars();
        Map<Integer, List<String>> winners = racingCarRepository.findWinners();
        List<RacingResultResponse> racingResultResponses = new ArrayList<>();
        playerHistory.keySet().forEach(gameId -> racingResultResponses.add(new RacingResultResponse(winners.get(gameId),playerHistory.get(gameId))));
        return racingResultResponses;
    }
}
