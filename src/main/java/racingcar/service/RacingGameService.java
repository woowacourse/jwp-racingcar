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
import racingcar.repository.PlayerRepository;
import racingcar.repository.RacingGameRepository;

@Service
public class RacingGameService {

    private final RacingGameRepository racingGameRepository;
    private final PlayerRepository playerRepository;

    protected RacingGameService(final RacingGameRepository racingGameRepository, final PlayerRepository playerRepository) {
        this.racingGameRepository = racingGameRepository;
        this.playerRepository = playerRepository;
    }

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

        final int racingGameId = racingGameRepository.save(winners, count);
        boolean isSaved = playerRepository.save(carGroup, racingGameId);

        if (!isSaved) {
            throw new IllegalStateException("레이싱 플레이어 저장에 실패하였습니다.");
        }

        return new RacingInfoResponse(winners, carGroup.getCars());
    }
}
