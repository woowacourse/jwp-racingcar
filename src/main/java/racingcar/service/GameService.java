package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.model.Cars;
import racingcar.util.RandomNumberGenerator;

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

    public void moveCars(Cars cars, int tryCount) {
        for (int count = 0; count < tryCount; count++) {
            cars.moveResult(numberGenerator);
        }

        // TODO: 2023/04/11 DB 저장
    }
}
