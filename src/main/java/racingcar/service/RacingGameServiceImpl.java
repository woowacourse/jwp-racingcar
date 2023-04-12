package racingcar.service;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import racingcar.controller.dto.RacingInfoResponse;
import racingcar.domain.CarGroup;
import racingcar.domain.Name;
import racingcar.domain.RacingGame;
import racingcar.domain.RacingResult;
import racingcar.domain.RandomNumberGenerator;
import racingcar.repository.PlayerRepositoryImpl;
import racingcar.repository.RacingGameRepositoryImpl;

@Service
@Transactional(readOnly = true)
public class RacingGameServiceImpl implements RacingGameService {

    private final RacingGameRepositoryImpl racingGameRepositoryImpl;
    private final PlayerRepositoryImpl playerRepositoryImpl;

    protected RacingGameServiceImpl(final RacingGameRepositoryImpl racingGameRepositoryImpl, final PlayerRepositoryImpl playerRepositoryImpl) {
        this.racingGameRepositoryImpl = racingGameRepositoryImpl;
        this.playerRepositoryImpl = playerRepositoryImpl;
    }

    @Override
    @Transactional
    public RacingInfoResponse race(final CarGroup carGroup, final int count) {
        final RacingGame racingGame = new RacingGame(carGroup, new RandomNumberGenerator());

        for (int i = 0; i < count; i++) {
            racingGame.race();
        }

        final RacingResult racingResult = racingGame.produceRacingResult();

        final String winners = racingResult.pickWinner()
                .stream()
                .map(Name::getName)
                .collect(Collectors.joining(","));

        final int racingGameId = racingGameRepositoryImpl.save(winners, count);
        boolean isSaved = playerRepositoryImpl.save(carGroup, racingGameId);

        if (!isSaved) {
            throw new IllegalStateException("레이싱 플레이어 저장에 실패하였습니다.");
        }

        return new RacingInfoResponse(winners, carGroup.getCars());
    }
}
