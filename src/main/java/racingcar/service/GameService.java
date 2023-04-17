package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.GameEntity;
import racingcar.dto.GameRecordResponseDto;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.RacingGameResponseDto;
import racingcar.model.Cars;
import racingcar.util.NameFormatConverter;
import racingcar.util.NumberGenerator;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final NumberGenerator numberGenerator;
    private final GameDao gameDao;
    private final CarDao carDao;

    @Autowired
    public GameService(NumberGenerator numberGenerator, CarDao carDao, GameDao gameDao) {
        this.numberGenerator = numberGenerator;
        this.gameDao = gameDao;
        this.carDao = carDao;
    }

    public RacingGameResponseDto executeRacingGame(RacingGameRequestDto racingGameRequestDto) {
        int tryCount = racingGameRequestDto.getCount();
        Cars cars = new Cars(NameFormatConverter.splitNameByDelimiter(racingGameRequestDto.getNames()));

        for (int count = 0; count < tryCount; count++) {
            cars.moveResult(numberGenerator);
        }

        String winners = NameFormatConverter.joinNameWithDelimiter(cars.getWinners());
        int gameId = gameDao.save(tryCount, winners);
        carDao.saveAll(gameId, cars.getCars());

        return new RacingGameResponseDto(cars.getWinners(), cars.getCars());
    }

    public List<GameRecordResponseDto> getGameRecord() {
        List<GameEntity> gameEntities = gameDao.findAll();
        return gameEntities.stream().map(gameEntity -> {
            List<CarEntity> carEntities = carDao.findAllById(gameEntity.getId());
            return new GameRecordResponseDto(gameEntity.getWinners(), carEntities);
        }).collect(Collectors.toUnmodifiableList());
    }
}
