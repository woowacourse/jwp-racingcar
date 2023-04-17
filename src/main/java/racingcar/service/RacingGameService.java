package racingcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.domain.CarGenerator;
import racingcar.domain.RacingGame;
import racingcar.domain.Winner;
import racingcar.dto.PlayResultResponseDto;
import racingcar.repository.RacingGameRepository;
import racingcar.utils.NumberGenerator;
import racingcar.utils.RandomNumberGenerator;

import java.util.List;

@Service
public class RacingGameService {
    private static final NumberGenerator numberGenerator = new RandomNumberGenerator();
    private static final CarGenerator carGenerator = new CarGenerator();
    private static final String DELIMETER = ",";

    @Autowired
    ConversionService conversionService;
    @Autowired
    RacingGameRepository racingGameRepository;

    public PlayResultResponseDto run(String names, Integer count) {
        List<Car> cars = carGenerator.generateCars(names.split(DELIMETER));
        RacingGame racingGame = new RacingGame(cars, count, numberGenerator);
        racingGame.run();
        Winner winner = racingGame.getWinner();

        racingGameRepository.insert(winner, count, cars);

        return new PlayResultResponseDto(winner, cars);
    }
}
