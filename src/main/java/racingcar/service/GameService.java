package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dto.PlayRequest;
import racingcar.model.Car;
import racingcar.model.Cars;
import racingcar.repository.GameDao;
import racingcar.util.CarNameValidator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    public static final String DELIMITER = ",";

    private final GameDao gameDao;
    private final CarNameValidator carNameValidator;

    @Autowired
    public GameService(final GameDao gameDao, final CarNameValidator carNameValidator) {
        this.gameDao = gameDao;
        this.carNameValidator = carNameValidator;
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

    public void play(final PlayRequest playRequest, final Cars cars) {
        for (int i = 0; i < playRequest.getCount(); i++) {
            cars.moveAll();
        }
    }
}
