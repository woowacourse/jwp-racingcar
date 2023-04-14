package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.RaceDto;
import racingcar.domain.Cars;
import racingcar.domain.Count;
import racingcar.domain.NumberPicker;
import racingcar.dto.CarPositionDto;

@Service
@Transactional(readOnly = true)
public class RacingCarsService {

    private final NumberPicker numberPicker;
    private final CarsRepository carsRepository;
    private final GamesRepository gamesRepository;

    public RacingCarsService(
            final NumberPicker numberPicker,
            final CarsRepository carsRepository,
            final GamesRepository gamesRepository) {
        this.numberPicker = numberPicker;
        this.carsRepository = carsRepository;
        this.gamesRepository = gamesRepository;
    }

    @Transactional
    public RaceDto race(final List<String> carsName, final int count) {
        final int gameId = gamesRepository.save(count);
        final Cars cars = new Cars(carsName);
        final Count tryCount = new Count(count);

        cars.race(tryCount, numberPicker);
        carsRepository.save(cars, gameId);

        return new RaceDto(gameId, cars.toDto(), cars.findWinner());
    }

    public List<CarPositionDto> findCarsWithPosition(final int gameId) {
        return carsRepository.findCars(gameId)
                .stream()
                .map(CarPositionDto::new)
                .collect(Collectors.toList());
    }

    public List<CarPositionDto> findWinners(final int gameId) {
        return carsRepository.findWinner(gameId)
                .stream()
                .map(CarPositionDto::new)
                .collect(Collectors.toList());
    }
}
