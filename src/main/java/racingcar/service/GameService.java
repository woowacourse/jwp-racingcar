package racingcar.service;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racingcar.dto.PlayRequest;
import racingcar.model.Car;
import racingcar.model.Cars;
import racingcar.repository.GameDao;
import racingcar.util.CarNameValidator;
import java.util.Arrays;
import java.util.List;

@Service
public class GameService {

    private static final String DELIMITER = ",";

    private final CarNameValidator carNameValidator;
    private final GameDao gameDao;

    @Autowired
    public GameService(final CarNameValidator carNameValidator, final GameDao gameDao) {
        this.carNameValidator = carNameValidator;
        this.gameDao = gameDao;
    }

    public Cars createCars(final PlayRequest playRequest) {
        String names = playRequest.getNames();

        List<String> carNames = splitNames(names);

        carNameValidator.validate(carNames);

        return new Cars(carNames.stream()
                .map(Car::new)
                .collect(toUnmodifiableList()));
    }

    private List<String> splitNames(final String names) {
        return Arrays.stream(names.split(DELIMITER, -1))
                .map(String::trim)
                .collect(toList());
    }

    public long saveGame(final PlayRequest playRequest) {
        return gameDao.insert(playRequest.getCount());
    }

    public void play(final PlayRequest playRequest, final Cars cars) {
        for (int i = 0; i < playRequest.getCount(); i++) {
            cars.moveAll();
        }
    }
}
