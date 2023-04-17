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
import racingcar.model.Car;
import racingcar.model.Cars;
import racingcar.model.RacingGame;
import racingcar.util.NameFormatConverter;
import racingcar.util.NumberGenerator;

import java.util.Comparator;
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

        RacingGame racingGame = new RacingGame(cars, tryCount);
        racingGame.race(numberGenerator);

        List<Car> winners = cars.getWinners();
        int gameId = gameDao.save(tryCount);
        carDao.saveAll(gameId, cars.getCars(), winners);

        return new RacingGameResponseDto(cars.getWinners(), cars.getCars());
    }

    public List<GameRecordResponseDto> getGameRecord() {
        List<GameEntity> gameEntities = gameDao.findAll();
        return gameEntities.stream().map(gameEntity -> {
            List<CarEntity> carEntities = carDao.findAllById(gameEntity.getId());
            int max = carEntities.stream()
                    .max(Comparator.comparing(CarEntity::getPosition)).orElseThrow().getPosition();

            List<CarEntity> result = carEntities.stream().filter(car -> car.getPosition() == max).collect(Collectors.toList());

            return new GameRecordResponseDto(result, carEntities);
        }).collect(Collectors.toUnmodifiableList());
    }
}
