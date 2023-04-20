package racingcar.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.dao.WinnerDao;
import racingcar.mapper.CarMapper;
import racingcar.dto.RacingGameDto;
import racingcar.mapper.RacingGameMapper;
import racingcar.dto.StartInformationDto;
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
    public RacingGameDto play(StartInformationDto startInformationDto) {
        RacingGame racingGame = createRacingGame(startInformationDto);
        racingGame.play();
        save(racingGame);
        return RacingGameMapper.toRacingGameDto(racingGame);
    }

    private RacingGame createRacingGame(StartInformationDto startInformationDto) {
        Cars cars = Cars.from(startInformationDto.getNames());
        MoveCount moveCount = MoveCount.from(startInformationDto.getCount());
        return new RacingGame(new ThresholdCarMoveManager(), cars, moveCount);
    }

    private void save(RacingGame racingGame) {
        long gameId = gameDao.insert(racingGame.getMoveCount());
        carDao.insert(CarMapper.mapCarsToCarEntities(racingGame.getCars()), gameId);
        winnerDao.insert(racingGame.getWinnerNames(), gameId);
    }

    @Transactional
    public List<RacingGameDto> queryHistory() {
        List<RacingGameDto> racingGameDtos = new ArrayList<>();
        for (GameEntity gameEntity: gameDao.selectAll()) {
            List<CarEntity> carEntities = carDao.selectByGameId(gameEntity.getId());
            List<String> winners = winnerDao.selectByGameId(gameEntity.getId());
            racingGameDtos.add(new RacingGameDto(winners, CarMapper.mapCarEntitiesToCarDtos(carEntities), gameEntity.getMoveCount()));
        }
        return racingGameDtos;
    }

}
