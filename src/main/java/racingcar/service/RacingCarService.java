package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
import racingcar.entity.GameEntity;
import racingcar.entity.ParticipatesEntity;
import racingcar.entity.PlayerEntity;

@Service
public class RacingCarService {

    private final GameDao gameDao;
    private final PlayerDao playerDao;
    private final ParticipatesDao participatesDao;

    public RacingCarService(final GameDao gameDao, final PlayerDao playerDao, final ParticipatesDao participatesDao) {
        this.gameDao = gameDao;
        this.playerDao = playerDao;
        this.participatesDao = participatesDao;
    }

    @Transactional
    public ResultResponse playGame(final NamesAndCountRequest namesAndCount) {
        final Cars cars = Cars.from(namesAndCount.getNames());
        final GameRound gameRound = new GameRound(namesAndCount.getCount());
        final GameManager gameManager = new GameManager(cars, gameRound, new RandomNumberGenerator());

        gameManager.play();

        final List<RacingCarResponse> racingCarResponses = gameManager.getResultCars();
        List<String> winnerNames = gameManager.decideWinner();
        saveGameAndPlayerAndParticipates(namesAndCount.getCount(), racingCarResponses, winnerNames);
        return convertResultResponse(racingCarResponses, winnerNames);
    }

    private ResultResponse convertResultResponse(final List<RacingCarResponse> racingCarResponses,
                                                 final List<String> winnerNames) {
        return new ResultResponse(winnerNames, racingCarResponses);
    }

    private void saveGameAndPlayerAndParticipates(final int trialCount,
                                                  final List<RacingCarResponse> racingCarResponses,
                                                  final List<String> winnerNames) {
        Long gameId = gameDao.save(trialCount);
        for (RacingCarResponse racingCarResponse : racingCarResponses) {
            String carName = racingCarResponse.getName();
            int carPosition = racingCarResponse.getPosition();
            Long playerId = findOrSavePlayer(carName);
            ParticipateDto participateDto = convertParticipate(winnerNames, gameId, carName, carPosition, playerId);
            participatesDao.save(participateDto);
        }
    }

    private Long findOrSavePlayer(final String carName) {
        Optional<PlayerEntity> playerEntity = playerDao.findByName(carName);
        if (playerEntity.isEmpty()) {
            return playerDao.save(carName);
        }
        return playerEntity.orElseThrow().getId();
    }

    private ParticipateDto convertParticipate(final List<String> winnerNames, final Long gameId, final String carName,
                                              final int carPosition, final Long playerId) {
        if (winnerNames.contains(carName)) {
            return new ParticipateDto(gameId, playerId, carPosition, true);
        }
        return new ParticipateDto(gameId, playerId, carPosition, false);
    }

    @Transactional
    public List<ResultResponse> findAllResult() {
        final List<ResultResponse> resultResponses = new ArrayList<>();
        gameDao.findAll()
                .stream()
                .map(GameEntity::getId)
                .forEach(id -> resultResponses.add(findGameResult(id)));
        return resultResponses;
    }

    private ResultResponse findGameResult(final Long id) {
        final List<ParticipatesEntity> participatesByGameIds = participatesDao.findByGameId(id);
        final List<String> winners = findWinners(participatesByGameIds);
        final List<RacingCarResponse> racingCarResponses = findRacingCarResponses(participatesByGameIds);
        return new ResultResponse(winners, racingCarResponses);
    }

    private List<RacingCarResponse> findRacingCarResponses(final List<ParticipatesEntity> participatesByGameIds) {
        return participatesByGameIds.stream()
                .map(participates -> new RacingCarResponse(playerDao.findNameById(participates.getPlayerId()),
                        participates.getPosition()))
                .collect(Collectors.toList());
    }

    private List<String> findWinners(final List<ParticipatesEntity> participatesByGameIds) {
        return participatesByGameIds.stream()
                .filter(ParticipatesEntity::getWinner)
                .map(participates -> playerDao.findNameById(participates.getPlayerId()))
                .collect(Collectors.toList());
    }
}
