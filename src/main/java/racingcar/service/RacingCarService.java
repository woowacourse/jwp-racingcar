package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.domain.RacingCarNames;
import racingcar.domain.RacingCars;
import racingcar.domain.RacingGame;
import racingcar.dto.PlayResponseDto;
import racingcar.dto.PlayResponseDtoConverter;
import racingcar.repository.RacingCarRepository;

@Service
public class RacingCarService {

    private final RacingCarRepository racingCarRepository;

    public RacingCarService(final RacingCarRepository racingCarRepository) {
        this.racingCarRepository = racingCarRepository;
    }

    public PlayResponseDto playGame(final int count, final RacingCarNames carNames) {
        RacingGame racingGame = RacingGame.of(carNames.createCars());
        racingGame.play(count);
        List<Car> cars = racingGame.racingCars();
        racingCarRepository.save(count, cars);
        return PlayResponseDtoConverter.from(cars, racingGame.findWinningCarNames());
    }

    public List<PlayResponseDto> findAllPlayRecords() {
        return racingCarRepository.getAll()
                .stream()
                .map(racingCars -> PlayResponseDtoConverter.from(
                        racingCars, new RacingCars(racingCars).findWinningCarNames()))
                .collect(Collectors.toList());
    }
}
