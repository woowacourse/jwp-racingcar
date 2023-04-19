package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.RaceDto;
import racingcar.domain.Car;
import racingcar.domain.NumberPicker;
import racingcar.domain.RacingGame;
import racingcar.dto.RacingGameDto;
import racingcar.dto.CarPositionDto;
import racingcar.repository.RacingGameRepository;

@Service
public class WebRacingGameService {

    private final NumberPicker numberPicker;
    private final RacingGameRepository racingGameRepository;

    public WebRacingGameService(final NumberPicker numberPicker, final RacingGameRepository racingGameRepository) {
        this.numberPicker = numberPicker;
        this.racingGameRepository = racingGameRepository;
    }

    @Transactional
    public RaceDto race(final List<String> carsName, final int count) {
        final RacingGame racingGame = new RacingGame(carsName, count);

        racingGame.race(numberPicker);

        final RacingGameDto racingGameDto = racingGame.findResult();

        final RacingGameDto savedRacingGameDto = racingGameRepository.save(racingGameDto);

        return new RaceDto(toDto(savedRacingGameDto.getTotalCars()), toDto(savedRacingGameDto.getWinners()));
    }

    @Transactional(readOnly = true)
    public List<RaceDto> findTotalGameHistory() {
        final List<RacingGameDto> history = racingGameRepository.findAll();

        return history.stream()
                .map(racingGameResult ->
                        new RaceDto(toDto(racingGameResult.getTotalCars()), toDto(racingGameResult.getWinners())))
                .collect(Collectors.toList());
    }

    private List<CarPositionDto> toDto(final List<Car> cars) {
        return cars.stream()
                .map(CarPositionDto::new)
                .collect(Collectors.toList());
    }
}
