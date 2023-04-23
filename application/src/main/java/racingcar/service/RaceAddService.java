package racingcar.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.NumberPicker;
import racingcar.domain.RacingGame;
import racingcar.repository.RacingGameRepository;
import racingcar.repository.WinnerRepository;

@Service
public class RaceAddService {

    private final NumberPicker numberPicker;
    private final RacingGameRepository racingGameRepository;
    private final WinnerRepository winnerRepository;

    public RaceAddService(final NumberPicker numberPicker, final RacingGameRepository racingGameRepository,
            final WinnerRepository winnerRepository) {
        this.numberPicker = numberPicker;
        this.racingGameRepository = racingGameRepository;
        this.winnerRepository = winnerRepository;
    }

    @Transactional
    public RacingGame addRace(final List<String> carsName, final int count) {
        final RacingGame racingGame = new RacingGame(carsName, count);
        racingGame.race(numberPicker);

        final RacingGame savedRacingGame = racingGameRepository.save(racingGame);
        winnerRepository.save(savedRacingGame.findWinner());
        return savedRacingGame;
    }
}
