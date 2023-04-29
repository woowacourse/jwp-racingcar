package racingcar.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.game.GameDao;
import racingcar.dao.participates.ParticipatesDao;
import racingcar.dao.player.PlayerDao;
import racingcar.domain.Cars;
import racingcar.domain.GameManager;
import racingcar.domain.GameRound;
import racingcar.domain.RandomNumberGenerator;
import racingcar.dto.NamesAndCountRequest;
import racingcar.dto.ParticipateDto;
import racingcar.dto.RacingCarResponse;
import racingcar.dto.ResultResponse;
import racingcar.entity.PlayerEntity;

@Service
@Transactional
public class PlayService {

    private final GameDao gameDao;
    private final PlayerDao playerDao;
    private final ParticipatesDao participatesDao;

    public PlayService(final GameDao gameDao, final PlayerDao playerDao, final ParticipatesDao participatesDao) {
        this.gameDao = gameDao;
        this.playerDao = playerDao;
        this.participatesDao = participatesDao;
    }

    public ResultResponse playGame(final NamesAndCountRequest namesAndCount) {
        final Cars cars = Cars.from(namesAndCount.getNames());
        final GameRound gameRound = new GameRound(namesAndCount.getCount());
        final GameManager gameManager = new GameManager(cars, gameRound, new RandomNumberGenerator());

        final Long gameId = gameDao.save(namesAndCount.getCount());
        return savePlayerAndParticipates(gameId, gameManager.getResultCars(), gameManager.decideWinner());
    }

    private ResultResponse savePlayerAndParticipates(final Long gameId,
                                                     final List<RacingCarResponse> racingCarResponses,
                                                     final List<String> winnerNames) {
        for (RacingCarResponse racingCarResponse : racingCarResponses) {
            String carName = racingCarResponse.getName();
            Long playerId = findOrSavePlayer(carName);
            saveParticipates(gameId, winnerNames, carName, racingCarResponse.getPosition(), playerId);
        }
        return new ResultResponse(winnerNames, racingCarResponses);
    }

    private Long findOrSavePlayer(final String carName) {
        Optional<PlayerEntity> playerEntity = playerDao.findByName(carName);
        if (playerEntity.isEmpty()) {
            return playerDao.save(carName);
        }
        return playerEntity.orElseThrow().getId();
    }

    private void saveParticipates(final Long gameId, final List<String> winnerNames, final String carName,
                                  final int carPosition, final Long playerId) {
        ParticipateDto participateDto = convertParticipate(winnerNames, gameId, carName, carPosition, playerId);
        participatesDao.save(participateDto);
    }

    private ParticipateDto convertParticipate(final List<String> winnerNames, final Long gameId, final String carName,
                                              final int carPosition, final Long playerId) {
        if (winnerNames.contains(carName)) {
            return new ParticipateDto(gameId, playerId, carPosition, true);
        }
        return new ParticipateDto(gameId, playerId, carPosition, false);
    }
}
