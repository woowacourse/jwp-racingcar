package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.*;
import racingcar.domain.numbergenerator.NumberGenerator;
import racingcar.dto.GameResultDto;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class RacingGameServiceImpl implements RacingGameService {

    private final NumberGenerator numberGenerator;
    private final RacingGameRepository racingGameRepository;

    public RacingGameServiceImpl(final NumberGenerator numberGenerator, final RacingGameRepository racingGameRepository) {
        this.numberGenerator = numberGenerator;
        this.racingGameRepository = racingGameRepository;
    }

    public GameResultDto play(final List<String> names, final int gameTime) {
        final Cars cars = new Cars(names.stream()
                .map(Car::new)
                .collect(Collectors.toList()));
        final GameTime time = new GameTime(String.valueOf(gameTime));
        final RacingGame racingGame = new RacingGame(cars, time);
        racingGame.play(numberGenerator);
        racingGameRepository.save(racingGame);
        final Winners result = racingGame.winners();
        return new GameResultDto(racingGame.getCars(), result);
    }
}
