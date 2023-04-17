package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.RaceDto;
import racingcar.domain.Car;
import racingcar.domain.NumberPicker;
import racingcar.domain.RacingGame;
import racingcar.dto.CarPositionDto;
import racingcar.repository.RacingGameRepository;
import racingcar.dao.entity.InsertGameEntity;

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

        final InsertGameEntity insertGameEntity = racingGameRepository.save(racingGame);
        final RacingGame saved = insertGameEntity.getRacingGame();

        return new RaceDto(insertGameEntity.getGameId(), toDto(saved.findResult()), toDto(saved.findWinner()));
    }

    private List<CarPositionDto> toDto(final List<Car> cars) {
        return cars.stream()
                .map(CarPositionDto::new)
                .collect(Collectors.toList());
    }
}
