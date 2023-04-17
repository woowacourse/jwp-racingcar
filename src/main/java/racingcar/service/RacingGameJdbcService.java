package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.controller.dto.RacingGameResponse;
import racingcar.domain.Cars;
import racingcar.domain.Name;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.repository.PlayerRepository;
import racingcar.repository.RacingGameRepository;

import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class RacingGameJdbcService implements RacingGameService {
    private final RacingGameRepository racingGameRepository;
    private final PlayerRepository playerRepository;

    protected RacingGameJdbcService(
            final RacingGameRepository racingGameRepository,
            final PlayerRepository playerRepository
    ) {
        this.racingGameRepository = racingGameRepository;
        this.playerRepository = playerRepository;
    }

    @Transactional
    @Override
    public RacingGameResponse race(final Cars cars, final int trial) {
        final RacingGame racingGame = new RacingGame(cars, new RandomNumberGenerator());
        racingGame.raceTimesBy(trial);
        final String winners = createWinners(racingGame);

        final int racingGameId = racingGameRepository.save(winners, trial);
        final boolean isSaved = playerRepository.save(cars, racingGameId);
        if (!isSaved) {
            throw new IllegalStateException("[ERROR] 레이싱 플레이어 저장에 실패하였습니다.");
        }

        return new RacingGameResponse(winners, cars.getRacingCars());
    }

    private String createWinners(final RacingGame racingGame) {
        return racingGame.createRacingResult()
                .pickWinner()
                .stream()
                .map(Name::getValue)
                .collect(Collectors.joining(","));
    }
}
