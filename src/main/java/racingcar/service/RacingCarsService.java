package racingcar.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.NumberPicker;
import racingcar.domain.RacingGame;
import racingcar.repository.RacingGameRepository;

@Service
public class RacingCarsService {

    private final NumberPicker numberPicker;
    private final RacingGameRepository racingGameRepository;

    public RacingCarsService(final NumberPicker numberPicker, final RacingGameRepository racingGameRepository) {
        this.numberPicker = numberPicker;
        this.racingGameRepository = racingGameRepository;
    }

    @Transactional
    public RacingGame race(final List<String> carsName, final int count) {
        final RacingGame racingGame = new RacingGame(carsName, count);
        racingGame.race(numberPicker);

        return racingGameRepository.insert(racingGame);
    }

    @Transactional(readOnly = true)
    public List<RacingGame> findRaceResult() {
        return racingGameRepository.findAll();
    }
}
