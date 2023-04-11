package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.model.Cars;
import racingcar.util.RandomNumberGenerator;

@Service
public class GameService {

    public void moveCars(Cars cars, int tryCount) {
        for (int count = 0; count < tryCount; count++) {
            cars.moveResult(new RandomNumberGenerator());
        }

        // TODO: 2023/04/11 DB 저장
    }
}
