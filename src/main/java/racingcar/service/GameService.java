package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.PlayRequest;
import racingcar.dto.PlayResponse;
import racingcar.model.Car;
import racingcar.model.Cars;
import racingcar.repository.GameDao;
import racingcar.util.CarNameValidator;
import racingcar.util.RepeatCountValidator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    public static final String DELIMITER = ",";

    private final RecordService recordService;
    private final GameDao gameDao;
    private final CarNameValidator carNameValidator;
    private final RepeatCountValidator repeatCountValidator;

    @Autowired
    public GameService(final RecordService recordService, final GameDao gameDao, final CarNameValidator carNameValidator, final RepeatCountValidator repeatCountValidator) {
        this.recordService = recordService;
        this.gameDao = gameDao;
        this.carNameValidator = carNameValidator;
        this.repeatCountValidator = repeatCountValidator;
    }

    public long saveGame(final PlayRequest playRequest) {
        return gameDao.insert(playRequest.getCount());
    }

    public Cars createCars(final PlayRequest playRequest) {
        String names = playRequest.getNames();
        List<String> trimCarNames = Arrays.stream(names.split(DELIMITER, -1))
                .map(String::trim)
                .collect(Collectors.toList());

        carNameValidator.validate(trimCarNames);

        return new Cars(trimCarNames.stream()
                .map(Car::new)
                .collect(Collectors.toUnmodifiableList()));
    }

    @Transactional
    public PlayResponse play(final PlayRequest playRequest) {
        Cars cars = createCars(playRequest);
        long gameId = saveGame(playRequest);

        int count = playRequest.getCount();
        repeatCountValidator.validate(count);

        for (int i = 0; i < count; i++) {
            cars.moveAll();
        }

        return recordService.result(gameId, cars);
    }
}
