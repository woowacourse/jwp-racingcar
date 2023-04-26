package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.dao.game.GameDao;
import racingcar.dao.participates.ParticipatesDao;
import racingcar.dao.player.PlayerDao;
import racingcar.dto.RacingCarResponse;
import racingcar.dto.ResultResponse;
import racingcar.entity.GameEntity;
import racingcar.entity.ParticipatesEntity;

@Service
public class ResultService {

    private final GameDao gameDao;
    private final PlayerDao playerDao;
    private final ParticipatesDao participatesDao;

    public ResultService(final GameDao gameDao, final PlayerDao playerDao, final ParticipatesDao participatesDao) {
        this.gameDao = gameDao;
        this.playerDao = playerDao;
        this.participatesDao = participatesDao;
    }


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
