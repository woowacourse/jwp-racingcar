package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.NumberPicker;
import racingcar.domain.RacingGame;
import racingcar.dto.CarPositionDto;
import racingcar.dto.RaceDto;
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
    public RaceDto race(final List<String> carsName, final int count) {
        final RacingGame racingGame = new RacingGame(carsName, count);
        racingGame.race(numberPicker);

        final RacingGame saved = racingGameRepository.insert(racingGame);

        return new RaceDto(saved.getGameId(), toDto(racingGame.findResult()), toDto(racingGame.findWinner()));
    }

    private List<CarPositionDto> toDto(final List<Car> cars) {
        return cars.stream()
                .map(CarPositionDto::new)
                .collect(Collectors.toList());
    }
}
