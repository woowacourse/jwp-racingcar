package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import racingcar.domain.Car;
import racingcar.domain.RacingCarGame;
import racingcar.dto.NamesAndCountDto;
import racingcar.dto.WinnersAndCarsDto;
import racingcar.domain.NumberGenerator;

@Service
public class MainRacingCarService {

    private final NumberGenerator numberGenerator;
    private final FindRecordService findRecordService;
    private final SaveRacingCarResultService saveRacingCarResultService;

    public MainRacingCarService(
        final NumberGenerator numberGenerator,
        final FindRecordService findRecordService,
        final SaveRacingCarResultService saveRacingCarResultService
    ) {
        this.numberGenerator = numberGenerator;
        this.findRecordService = findRecordService;
        this.saveRacingCarResultService = saveRacingCarResultService;
    }

    public List<WinnersAndCarsDto> findAllRecords() {
        final List<RacingCarResult> racingCarResults = findRecordService.findAllRecords();
        return racingCarResults.stream()
            .map(WinnersAndCarsDto::from)
            .collect(Collectors.toList());
    }

    public WinnersAndCarsDto raceCar(final NamesAndCountDto namesAndCountDto) {
        final List<String> names = List.of(namesAndCountDto.getNames().split(","));
        final int attempt = namesAndCountDto.getCount();

        final RacingCarGame racingCarGame = new RacingCarGame(names, attempt, numberGenerator);
        final List<String> winners = racingCarGame.findWinners();
        final List<Car> cars = racingCarGame.getCars();
        RacingCarResult racingCarResult = new RacingCarResult(winners, cars, attempt);
        saveRacingCarResultService.saveRacingCarResult(racingCarResult);

        return WinnersAndCarsDto.from(racingCarResult);
    }
}
