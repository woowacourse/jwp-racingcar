package racingcar.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.dao.WinnerDao;
import racingcar.dto.CarDto;
import racingcar.dto.RacingGameResultDto;
import racingcar.dto.RacingGameSetUpDto;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;
import racingcar.model.MoveCount;
import racingcar.model.RacingGame;
import racingcar.model.car.Cars;
import racingcar.model.manager.ThresholdCarMoveManager;

import java.util.ArrayList;
import java.util.List;

@Service
public class RacingGameService {

    private final GameDao gameDao;
    private final WinnerDao winnerDao;
    private final CarDao carDao;

    public RacingGameService(GameDao gameDao, WinnerDao winnerDao, CarDao carDao) {
        this.gameDao = gameDao;
        this.winnerDao = winnerDao;
        this.carDao = carDao;
    }

    @Transactional
    public RacingGameResultDto play(RacingGameSetUpDto racingGameSetUpDto) {
        RacingGame racingGame = createRacingGame(racingGameSetUpDto);
        racingGame.play();
        save(racingGame);
        return RacingGameResultDto.from(racingGame);
    }

    private RacingGame createRacingGame(RacingGameSetUpDto racingGameSetUpDto) {
        Cars cars = Cars.from(racingGameSetUpDto.getNames());
        MoveCount moveCount = MoveCount.from(racingGameSetUpDto.getCount());
        return new RacingGame(new ThresholdCarMoveManager(), cars, moveCount);
    }

    private void save(RacingGame racingGame) {
        long gameId = gameDao.insert(racingGame.getMoveCount());
        carDao.insert(CarEntity.from(racingGame.getCars()), gameId);
        winnerDao.insert(racingGame.getWinnerNames(), gameId);
    }

    @Transactional(readOnly = true)
    public List<RacingGameResultDto> queryHistory() {
        List<RacingGameResultDto> racingGameResultDtos = new ArrayList<>();
        for (GameEntity gameEntity : gameDao.selectAll()) {
            List<CarEntity> carEntities = carDao.selectByGameId(gameEntity.getId());
            List<String> winners = winnerDao.selectByGameId(gameEntity.getId());
            racingGameResultDtos.add(new RacingGameResultDto(winners, CarDto.fromEntity(carEntities), gameEntity.getMoveCount()));
        }
        return racingGameResultDtos;
    }

}
