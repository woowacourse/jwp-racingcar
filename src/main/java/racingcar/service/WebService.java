package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.controller.dto.GameResponseDto;
import racingcar.dao.web.RacingCarWebDao;
import racingcar.dao.web.RacingGameWebDao;
import racingcar.dao.web.WinnersWebDao;
import racingcar.domain.Winner;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebService extends RacingCarService {

    @Autowired
    public WebService(RacingCarWebDao racingCarWebDao, RacingGameWebDao racingGameWebDao, WinnersWebDao winnersWebDao) {
        super(racingCarWebDao, racingGameWebDao, winnersWebDao);
    }

    public List<GameResponseDto> getGameHistory() {
        List<GameEntity> gameEntities = racingGameDao.findAll();
        return generateGameResultsPerGame(gameEntities);
    }

    private List<GameResponseDto> generateGameResultsPerGame(List<GameEntity> gameEntities) {
        List<GameResponseDto> gameResponseDtos = new ArrayList<>();
        for (GameEntity gameEntity : gameEntities) {
            List<CarEntity> carsByGameId = racingCarDao.findCarsByGameId(gameEntity.getId());
            List<Winner> winnersByGameId = winnersDao.getWinnerNamesByGameId(gameEntity.getId());
            gameResponseDtos.add(generateGameResponseDto(gameEntity.getId(), carsByGameId, winnersByGameId));
        }
        return gameResponseDtos;
    }

}
