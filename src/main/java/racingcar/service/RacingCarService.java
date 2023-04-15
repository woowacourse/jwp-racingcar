package racingcar.service;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.RacingCars;
import racingcar.dto.RacingCarDto;
import racingcar.repository.RacingCarRepository;
import racingcar.utils.NumberGenerator;

@Service
public class RacingCarService {
    private static final int MAX_TRY_COUNT = 25;
    private final RacingCarRepository racingCarRepository;
    private final NumberGenerator numberGenerator;

    public RacingCarService(RacingCarRepository racingCarRepository, NumberGenerator numberGenerator) {
        this.racingCarRepository = racingCarRepository;
        this.numberGenerator = numberGenerator;
    }

    @Transactional
    public int playRacingGame(List<String> carNames, int tryCount) {
        validateCount(tryCount);
        int gameId = racingCarRepository.saveGame(tryCount);
        RacingCars racingCars = carNames.stream()
                .map(Car::new)
                .collect(collectingAndThen(toList(), RacingCars::new));
        for (int i = 0; i < tryCount; i++) {
            racingCars.moveCars(numberGenerator);
        }
        racingCarRepository.saveCars(gameId, racingCars.getCars());
        racingCarRepository.saveWinner(gameId, racingCars.getWinners());
        return gameId;
    }

    private void validateCount(int tryCount) {
        if (tryCount <= 0) {
            throw new IllegalArgumentException("[ERROR]: 시도 횟수는 0보다 커야 합니다.");
        }
        if (tryCount > MAX_TRY_COUNT) {
            throw new IllegalArgumentException("[ERROR]: 시도 횟수는 " + MAX_TRY_COUNT + "이하여야 합니다.");
        }
    }

    public String findWinners(int gameId) {
        return racingCarRepository.findWinners(gameId);
    }

    public List<RacingCarDto> findRacingCars(int gameId) {
        return racingCarRepository.findRacingCars(gameId);
    }
}
