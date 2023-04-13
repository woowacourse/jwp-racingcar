package racingcar.service;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import racingcar.controller.dto.RacingInfoResponse;
import racingcar.domain.CarGroup;
import racingcar.domain.Name;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.repository.PlayerRepository;
import racingcar.repository.RacingGameRepository;

@Service
@Transactional(readOnly = true)
public class RacingGameServiceImpl implements RacingGameService {

    private final RacingGameRepository racingGameRepository;
    private final PlayerRepository playerRepository;

    protected RacingGameServiceImpl(final RacingGameRepository racingGameRepository, final PlayerRepository playerRepository) {
        this.racingGameRepository = racingGameRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    @Transactional
    public RacingInfoResponse race(final CarGroup carGroup, final int count) {
        final RacingGame racingGame = new RacingGame(carGroup, new RandomNumberGenerator());
        raceBy(count, racingGame);
        final String winners = createWinners(racingGame);

        final int racingGameId = racingGameRepository.save(winners, count);
        final boolean isSaved = playerRepository.save(carGroup, racingGameId);
        if (!isSaved) {
            throw new IllegalStateException("[ERROR] 레이싱 플레이어 저장에 실패하였습니다.");
        }

        return new RacingInfoResponse(winners, carGroup.getCars());
    }

    private void raceBy(final int count, final RacingGame racingGame) {
        for (int i = 0; i < count; i++) {
            racingGame.race();
        }
    }

    private String createWinners(final RacingGame racingGame) {
        return racingGame.produceRacingResult()
                .pickWinner()
                .stream()
                .map(Name::getName)
                .collect(Collectors.joining(","));
    }
}
